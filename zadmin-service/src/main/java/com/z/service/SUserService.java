package com.z.service;


import com.z.bean.admin.req.user.UserInfoRes;
import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.base.Response;


public interface SUserService {

    Response<Boolean> register(LoginReq req);

    Response<UserInfoRes> getUserInfo();
}
