package com.z.service;

import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.admin.res.login.LoginRes;
import com.z.entity.dto.SecurityUserDto;
import com.z.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginRes login(LoginReq req) {
        String password = req.getPassword();
        String username = req.getUsername();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);//放在当前线程上下文 todo 密码错误异常处理
        SecurityUserDto jwtUserDto = (SecurityUserDto) authentication.getPrincipal();
        String token = JwtUtil.createJWT(jwtUserDto.getUser().getId().toString());
        LoginRes res = new LoginRes();
        res.setToken(token);
        return res;
    }
}
