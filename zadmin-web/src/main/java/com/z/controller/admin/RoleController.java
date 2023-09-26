package com.z.controller.admin;

import com.z.bean.admin.req.role.RelateRoleMenusReq;
import com.z.bean.admin.req.role.RoleAddReq;
import com.z.bean.admin.req.role.RoleEditReq;
import com.z.bean.admin.req.role.RoleListReq;
import com.z.bean.base.Response;
import com.z.service.SRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SRoleService roleService;

    @GetMapping("/getAllRole")
    public Response getAllRole(){
        return roleService.getAllRole();
    }

    @GetMapping("/getRoleByUid")
    public Response getRoleByUid(@PathParam("uid") Long uid){
        return roleService.getRoleByUid(uid);
    }

    @PostMapping("/relateRoleMenus")
    public Response relateRoleMenus(@RequestBody @Valid RelateRoleMenusReq req){
        return roleService.relateRoleMenus(req);
    }

    @GetMapping("/getRoleMenus")
    public Response getRoleMenus(@RequestParam("rid") String rid){
        return roleService.getRoleMenus(rid);
    }

    @PostMapping("/editRole")
    public Response editRole(@RequestBody @Valid RoleEditReq req){
        return roleService.editRole(req);
    }

    @PostMapping("/getRoleList")
    public Response getRoleList(@RequestBody RoleListReq req){
        return roleService.getRoleList(req);
    }

    @PostMapping("/addRole")
    public Response<Boolean> addRole(@RequestBody RoleAddReq req){
        return roleService.addRole(req);
    }

}
