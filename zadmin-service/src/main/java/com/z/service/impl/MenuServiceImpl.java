package com.z.service.impl;

import cn.hutool.core.util.StrUtil;
import com.z.bean.admin.req.menu.AddMenuReq;
import com.z.bean.base.Response;
import com.z.entity.sys.SMenu;
import com.z.service.SMenuService;
import com.z.sys.mapper.SMenuMapper;
import com.z.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MenuServiceImpl implements SMenuService{

    @Autowired
    private SMenuMapper menuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response addMenu(AddMenuReq req) {
        if(StrUtil.isBlank(req.getMenuName())){
            Response.error("菜单名不能为空！");
        }
        SMenu newMenu = new SMenu();
        newMenu.setCreateUser(SecurityUtils.getSecurityUser().getUsername());
        newMenu.setCreateTime(new Date());
        BeanUtils.copyProperties(req,newMenu);
        menuMapper.insert(newMenu);
        return Response.success();
    }
}
