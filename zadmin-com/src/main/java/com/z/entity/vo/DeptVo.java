package com.z.entity.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DeptVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门id
     */
    private Long pId;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 显示顺序
     */
    private Integer deptSort;


    /**
     * 创建人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date updateTime;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    List<DeptVo> children;

}
