package com.z.controller;

import com.z.bean.admin.req.role.RoleAddReq;
import com.z.bean.admin.req.role.RoleEditReq;
import com.z.bean.admin.req.role.RoleListReq;
import com.z.bean.base.Response;
import com.z.service.SRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SRoleService roleService;

    @PostMapping("edit")
    public Response edit(@RequestBody @Valid RoleEditReq req){
        return roleService.edit(req);
    }

    @PostMapping("list")
    public Response list(@RequestBody RoleListReq req){
        return roleService.list(req);
    }

    @PostMapping("/addRole")
    public Response<Boolean> addRole(@RequestBody RoleAddReq req){
        return roleService.addRole(req);
    }




}
