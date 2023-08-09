package com.z.bean.admin.req.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditUserReq {

    @NotNull(message = "用户Id不能为空!")
    private Long id;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 账号状态 0-正常 1-封禁
     */
    @NotNull(message = "账号状态不能为空!")
    private Integer userStatus;

}
