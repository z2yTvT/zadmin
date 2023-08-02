package com.z.controller;

import cn.hutool.core.util.StrUtil;
import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.base.Response;
import com.z.service.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private SUserService userService;

    @PostMapping("/register")
    public Response<Boolean> register(@RequestBody LoginReq req){
        if(StrUtil.isBlank(req.getPassword()) || StrUtil.isBlank(req.getUsername())){
            return Response.error("用户名或密码不能为空");
        }
        if(req.getUsername().length() < 6 || req.getUsername().length() > 15){
            return Response.error("账户长度必须在6到15个字符之间");
        }
        if(req.getPassword().length() < 6 || req.getPassword().length() > 15){
            return Response.error("密码长度必须在6到15个字符之间");
        }
        return userService.register(req);
    }
}
