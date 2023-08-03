package com.z.controller;

import com.z.bean.admin.req.menu.AddMenuReq;
import com.z.bean.base.Response;
import com.z.service.SMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SMenuService menuService;

    @PostMapping("/addMenu")
    public Response addMenu(@RequestBody AddMenuReq req){
        return menuService.addMenu(req);
    }

}
