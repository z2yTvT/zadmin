package com.z.config.filter;


import cn.hutool.core.util.StrUtil;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.dto.SecurityUserDto;
import com.z.entity.sys.SUser;
import com.z.service.SRoleService;
import com.z.sys.mapper.SUserMapper;
import com.z.utils.JwtUtil;
import io.jsonwebtoken.Claims;
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
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private SUserMapper userMapper;

    @Autowired
    private SRoleService roleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(StrUtil.isBlank(token) || !token.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        token = token.substring(7);
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        }catch (Exception e){
            throw new RuntimeException(e);//todo 异常处理
        }
        SUser user = userMapper.selectById(userId);//todo SecurityUserDto && 权限todo
        List<AuthorityDto> authorities = roleService.getAuthorities(user);
        SecurityUserDto securityUserDto = new SecurityUserDto(user,authorities);
        if(user == null){
            throw new RuntimeException("用户未登录");
        }
        //将认证后的信息存储到Spring Security的上下文中，以便后续的访问控制决策使用。
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUserDto,null, securityUserDto.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
