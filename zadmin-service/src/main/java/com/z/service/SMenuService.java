package com.z.service;


import com.z.bean.admin.req.menu.AddMenuReq;
import com.z.bean.base.Response;

public interface SMenuService  {

    Response addMenu(AddMenuReq req);
}
