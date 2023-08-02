package com.z.service;


import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.base.Response;

/**
* @author zyyz
* @description 针对表【s_user(用户)】的数据库操作Service
* @createDate 2023-07-31 23:30:15
*/
public interface SUserService {

    Response<Boolean> register(LoginReq req);

}
