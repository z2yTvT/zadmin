package com.z.service.impl;

import cn.hutool.core.util.StrUtil;
import com.z.bean.admin.req.menu.AddMenuReq;
import com.z.bean.base.Response;
import com.z.constant.SystemConstants;
import com.z.entity.dto.RouteDto;
import com.z.entity.sys.SMenu;
import com.z.entity.vo.MenuTreeOptVo;
import com.z.entity.vo.RouteVo;
import com.z.service.SMenuService;
import com.z.sys.mapper.SMenuMapper;
import com.z.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements SMenuService{

    @Autowired
    private SMenuMapper menuMapper;



    @Override
    public Response getSelectedMenus(Long rid) {
        List<Long> res = menuMapper.getSelectedMenus(rid);
        return Response.success(res);
    }

    @Override
    public Response<List<MenuTreeOptVo>> getMenuTreeOpt() {
        List<RouteDto> allMenu = menuMapper.getAllRoutes();
        List<MenuTreeOptVo> res = recurMenu2Tree(SystemConstants.MENU_PARENT_ID,allMenu);
        return Response.success(res);
    }


    private List<MenuTreeOptVo> recurMenu2Tree(Long pid, List<RouteDto> allMenu) {
        List<MenuTreeOptVo> menuTreeOptVoList = new ArrayList<>();
        menuTreeOptVoList = allMenu.stream().filter(a -> a.getPId().equals(pid))
                .map(curMenu -> {
                    MenuTreeOptVo optVo = new MenuTreeOptVo();
                    optVo.setId(curMenu.getId());
                    optVo.setLabel(curMenu.getMenuName());
                    optVo.setChildren(recurMenu2Tree(curMenu.getId(), allMenu));
                    return optVo;
                }).collect(Collectors.toList());
        return menuTreeOptVoList;
    }

    @Override
    public Response<List<RouteVo>> getRoutes() {
        //查询每一个菜单的能访问的角色id集合，并且将菜单打成层级结构，菜单类型不能为按钮类型
        //查询出所有菜单集合，没有层级关系
        List<RouteDto> allMenu = menuMapper.getAllRoutes();
        List<RouteVo> res = recurRoutes2Tree(SystemConstants.MENU_PARENT_ID,allMenu);
        return Response.success(res);
    }



    private List<RouteVo> recurRoutes2Tree(Long menuParentId, List<RouteDto> menus) {
        List<RouteVo> routeVoList = new ArrayList<>();
        routeVoList = menus.stream().filter(a -> a.getPId().equals(menuParentId))
                .map(curMenu -> {
                    RouteVo routeVo = new RouteVo();
                    Integer menuType = curMenu.getMenuType();
                    if (menuType == 2) {
                        routeVo.setName(curMenu.getPath());
                    }
                    routeVo.setComponent(curMenu.getComponent());
                    routeVo.setPath(curMenu.getPath());
                    routeVo.setName(curMenu.getMenuName());

                    RouteVo.Meta meta = new RouteVo.Meta();
                    meta.setHidden(curMenu.getIsHidden() == 1);
                    meta.setIcon(curMenu.getIcon());
                    meta.setKeepAlive(Boolean.TRUE);
                    meta.setRoleKeys(curMenu.getRoleKeys());
                    meta.setTitle(curMenu.getMenuName());
                    routeVo.setMeta(meta);
                    List<RouteVo> sub = recurRoutes2Tree(curMenu.getId(), menus);
                    routeVo.setChildren(sub);
                    return routeVo;
                }).collect(Collectors.toList());
        return routeVoList;
    }

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
