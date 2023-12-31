package com.z.config.filter;


import cn.hutool.core.util.StrUtil;
import com.z.bean.enums.ResponseCodeEnum;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.dto.SecurityUserDto;
import com.z.entity.sys.SUser;
import com.z.exception.ServiceException;
import com.z.service.SRoleService;
import com.z.sys.mapper.SUserMapper;
import com.z.utils.JwtUtil;
import com.z.utils.ResponseUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private SUserMapper userMapper;

    @Autowired
    private SRoleService roleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.substring(7);
        Long userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.get("userId", Long.class);
            SUser user = userMapper.selectById(userId);
            if (user == null) {
                throw new ServiceException(ResponseCodeEnum.USER_NOT_EXIST);
            }
            List<AuthorityDto> authorities = roleService.getAuthorities(user);
            Integer dataScope = roleService.getDataScope(user);
            SecurityUserDto securityUserDto = new SecurityUserDto(user, authorities, dataScope);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUserDto, null, securityUserDto.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | ServiceException  e) {
            if (e instanceof ExpiredJwtException) {
                ResponseUtils.errResp(response, ResponseCodeEnum.EXPIRE_TOKEN);
                return;
            }
            if (e instanceof ServiceException) {
                ResponseUtils.errResp(response, ResponseCodeEnum.USER_NOT_EXIST);
                return;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            ResponseUtils.errResp(response, ResponseCodeEnum.CODE_500);
        }

    }
}
