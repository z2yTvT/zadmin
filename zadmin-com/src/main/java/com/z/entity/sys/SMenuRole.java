package com.z.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单权限关联表
 * @TableName s_menu_role
 */
@TableName(value ="s_menu_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMenuRole implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单id
     */
    private Long mId;

    /**
     * 权限id
     */
    private Long rId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public SMenuRole(Long mId, Long rId) {
        this.mId = mId;
        this.rId = rId;
    }
}