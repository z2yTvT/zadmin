package com.z.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MenuVo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 父菜单id
     */
    private Long pId;

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
     * 是否隐藏
     */
    private Integer hidden;

    /**
     * 图标
     */
    private String icon;

    /**
     * 组件
     */
    private String component;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 排序
     */
    private Integer menuSort;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 子菜单
     */
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<MenuVo> children;

}
