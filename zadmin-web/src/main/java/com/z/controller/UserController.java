package com.z.controller;

import com.z.bean.admin.req.user.UserInfoRes;
import com.z.bean.base.Response;
import com.z.service.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SUserService userService;

    @GetMapping("/getUserInfo")
    public Response<UserInfoRes> getUserInfo(){
        return userService.getUserInfo();
    }
}
