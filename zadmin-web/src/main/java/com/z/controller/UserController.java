package com.z.controller;

import com.z.bean.admin.req.user.AddUserReq;
import com.z.bean.admin.req.user.UserInfoRes;
import com.z.bean.base.Response;
import com.z.service.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SUserService userService;

    @PostMapping("/addUser")
    public Response addUser(@RequestBody @Valid AddUserReq req){
        return userService.addUser(req);
    }

    @GetMapping("/getUserInfo")
    public Response<UserInfoRes> getUserInfo(){
        return userService.getUserInfo();
    }
}
