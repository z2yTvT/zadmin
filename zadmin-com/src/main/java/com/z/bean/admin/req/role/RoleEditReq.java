package com.z.bean.admin.req.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class RoleEditReq {

    /**
     * id
     */
    private Long id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空！")
    private String roleName;

    /**
     * 角色权限标识
     */
    private String roleKey;

    /**
     * 数据权限范围
     */
    private Integer dataScope;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 备注
     */
    private String remark;
}
