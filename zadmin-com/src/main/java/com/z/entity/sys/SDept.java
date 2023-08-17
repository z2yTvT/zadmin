package com.z.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门
 * @TableName s_dept
 */
@TableName(value ="s_dept")
@Data
public class SDept implements Serializable {
    /**
     * 主键
     */
    @TableId
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
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}