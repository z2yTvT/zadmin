package com.z.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.bean.admin.req.user.AddUserReq;
import com.z.bean.admin.req.user.EditUserReq;
import com.z.bean.admin.req.user.UserInfoRes;
import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.admin.req.user.UserListReq;
import com.z.bean.admin.res.User.UserListRes;
import com.z.bean.base.Response;


public interface SUserService {

    Response<Boolean> register(LoginReq req);

    Response<UserInfoRes> getUserInfo();

    Response addUser(AddUserReq req);

    Response getUserList(UserListReq req);

    Response editUser(EditUserReq req);
}
