package com.z.bean.admin.req.user;

import lombok.Data;

import java.util.List;

@Data
public class RelateUserRoleReq {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 绑定角色集合
     */
    private List<Long> roleIds;

}
