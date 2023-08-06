package com.z.bean.admin.req.role;

import com.z.bean.admin.req.BaseReq;
import lombok.Data;

@Data
public class RoleListReq extends BaseReq {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限标识
     */
    private String roleKey;
}
