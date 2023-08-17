package com.z.bean.admin.req.dept;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddDeptReq {
    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空！")
    private String deptName;

    /**
     * 父部门id
     */
    @NotNull(message = "父菜单不能为空！")
    private Long pId;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 显示顺序
     */
    private Integer deptSort;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }
}
