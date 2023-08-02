package com.z.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户角色关联表
 * @TableName s_user_role
 */
@TableName(value ="s_user_role")
@Data
public class SUserRole implements Serializable {
    /**
     * 主键id

     */
    private Long id;

    /**
     * 用户id
     */
    private Long uId;

    /**
     * 角色id
     */
    private Long rId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}