package com.z.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class RouteVo {

    /**
     * 组件名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 跳转路径
     */
    private String redirect;

    /**
     * 组件属性
     */
    private Meta meta;

    /**
     * 子路由集合
     */
    private List<RouteVo> children;

    @Data
    public static class Meta{

        private String tittle;

        private String icon;

        private Boolean hidden;

        private Boolean keepAlive;

        /**
         * 角色 id集合 [1,2,3]
         */
        private List<Integer> roleIds;

    }
}
