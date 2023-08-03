package com.z.controller;

import cn.hutool.core.util.StrUtil;
import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.admin.res.login.LoginRes;
import com.z.bean.base.Response;
import com.z.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Response<LoginRes> login(@RequestBody LoginReq req){
        if(StrUtil.isBlank(req.getUsername()) || StrUtil.isBlank(req.getPassword())){
            return Response.error("用户名和密码不能为空！");
        }
        LoginRes res = loginService.login(req);
        return Response.success(res);
    }
}
