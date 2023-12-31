package com.z.service;


import com.z.bean.admin.req.menu.AddMenuReq;
import com.z.bean.admin.req.menu.EditMenuReq;
import com.z.bean.admin.req.user.MenuListReq;
import com.z.bean.base.Response;
import com.z.entity.vo.RouteVo;

import java.util.List;

public interface SMenuService  {

    Response addMenu(AddMenuReq req);

    Response<List<RouteVo>> getRoutes();

    Response getMenuTreeOpt();

    Response getSelectedMenus(Long rid);

    Response getMenuList(MenuListReq req);

    Response getMenuDetail(Long id);

    Response edit(EditMenuReq req);
}
