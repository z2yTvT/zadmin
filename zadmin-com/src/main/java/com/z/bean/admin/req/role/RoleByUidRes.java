package com.z.bean.admin.req.role;

import lombok.Data;

@Data
public class RoleByUidRes {

    private String roleName;

    private Long id;

    public RoleByUidRes(String roleName, Long id) {
        this.roleName = roleName;
        this.id = id;
    }
}
