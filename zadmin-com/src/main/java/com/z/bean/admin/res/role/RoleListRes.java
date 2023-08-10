package com.z.bean.admin.res.role;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoleListRes {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限标识
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 数据权限范围
     */
    private String dataScope;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
