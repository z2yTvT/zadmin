package com.z.bean.admin.res.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserListRes {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 所属角色名称
     */
    private String roleName;

    /**
     * 账号状态 0-正常 1-封禁
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
