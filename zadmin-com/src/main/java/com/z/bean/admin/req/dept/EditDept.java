package com.z.bean.admin.req.dept;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class EditDept extends AddDeptReq{

    @NotNull(message = "部门不能为空！")
    private Long id;
}
