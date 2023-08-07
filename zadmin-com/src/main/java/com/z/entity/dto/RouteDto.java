package com.z.entity.dto;


import lombok.Data;

import java.util.List;

@Data
public class RouteDto {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * '权限标识'
     */
    private String perm;

    /**
     * 菜单类型 1:目录 2：菜单 3：按钮
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
    private Integer isHidden;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer menuSort;

    /**
     * 跳转路径
     */
    private String redirect;

    /**
     * 拥有菜单角色Id集合
     */
    private List<Integer> roleIds;

}
