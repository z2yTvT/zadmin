package com.z.controller.admin;

import com.z.bean.admin.req.menu.AddMenuReq;
import com.z.bean.admin.req.menu.EditMenuReq;
import com.z.bean.admin.req.user.MenuListReq;
import com.z.bean.base.Response;
import com.z.entity.vo.RouteVo;
import com.z.service.SMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SMenuService menuService;

    @PostMapping("editMenu")
    public Response editMenu(@RequestBody EditMenuReq req){
        return menuService.edit(req);
    }

    @GetMapping("/getMenuDetail")
    public Response getMenuDetail(@RequestParam("id")Long id){
        return menuService.getMenuDetail(id);
    }

    @PostMapping("getMenuList")
    public Response getMenuList(@RequestBody MenuListReq req){
        return menuService.getMenuList(req);
    }

    @GetMapping("/getSelectedMenus")
    public Response getSelectedMenus(@RequestParam("rid")Long rid){
        return menuService.getSelectedMenus(rid);
    }

    @GetMapping("/getMenuTreeOpt")
    public Response getMenuTreeOpt(){
        return menuService.getMenuTreeOpt();
    }

    @PostMapping("/addMenu")
    public Response addMenu(@RequestBody AddMenuReq req){
        return menuService.addMenu(req);
    }

    @GetMapping("/getRoutes")
    public Response<List<RouteVo>> getRoutes(){
        return menuService.getRoutes();
    }

}
