package com.z.bean.admin.req.menu;

import lombok.Data;

@Data
public class AddMenuReq {

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * '权限标识'
     */
    private String perm;

    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 父菜单id
     */
    private Long pId;


    /**
     * 组件
     */
    private String component;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏
     */
    private Integer hidden;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer menuSort;

}
