package com.z.controller;

import com.z.bean.admin.req.menu.AddMenuReq;
import com.z.bean.base.Response;
import com.z.entity.vo.RouteVo;
import com.z.service.SMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {

    @Autowired
    private SMenuService menuService;

    @PostMapping("/addMenu")
    public Response addMenu(@RequestBody AddMenuReq req){
        return menuService.addMenu(req);
    }

    @GetMapping("/getRoutes")
    public Response<List<RouteVo>> getRoutes(){
        return menuService.getRoutes();
    }

}
