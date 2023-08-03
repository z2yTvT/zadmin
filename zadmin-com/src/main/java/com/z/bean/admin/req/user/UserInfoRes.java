package com.z.bean.admin.req.user;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoRes {

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色集合
     */
    private List<String> roles;

    /**
     * 权限集合
     */
    private List<String> perms;
}
