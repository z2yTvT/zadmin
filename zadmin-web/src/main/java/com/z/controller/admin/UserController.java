package com.z.controller.admin;

import com.z.bean.admin.req.user.*;
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

    @PostMapping("/relateUserRole")
    public Response relateUserRole(@RequestBody RelateUserRoleReq req){
        return userService.relateUserRole(req);
    }

    @PostMapping("editUser")
    public Response editUser(@RequestBody @Valid EditUserReq req){
        return userService.editUser(req);
    }

    @PostMapping("/getUserList")
    public Response getUserList(@RequestBody UserListReq req){
        return userService.getUserList(req);
    }

    @PostMapping("/addUser")
    public Response addUser(@RequestBody @Valid AddUserReq req){
        return userService.addUser(req);
    }

    @GetMapping("/getUserInfo")
    public Response<UserInfoRes> getUserInfo(){
        return userService.getUserInfo();
    }
}
