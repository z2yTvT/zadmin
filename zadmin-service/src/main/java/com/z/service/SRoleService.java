package com.z.service;


import com.z.bean.admin.req.role.RoleAddReq;
import com.z.bean.base.Response;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.sys.SUser;

import java.util.List;
import java.util.Set;


public interface SRoleService {

    //获取用户所有权限
    List<AuthorityDto> getAuthorities(SUser user);

    //新增角色
    Response<Boolean> addRole(RoleAddReq req);
}
