package com.z.controller;

import com.z.bean.admin.req.role.RoleAddReq;
import com.z.bean.base.Response;
import com.z.service.SRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SRoleService roleService;

    @PostMapping("/addRole")
    public Response<Boolean> addRole(@RequestBody RoleAddReq req){
        return roleService.addRole(req);
    }

}
