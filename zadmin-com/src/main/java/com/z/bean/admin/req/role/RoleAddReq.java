package com.z.bean.admin.req.role;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class RoleAddReq {
    /**
     * 角色名称
     */
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
     * 备注
     */
    private String remark;
}
