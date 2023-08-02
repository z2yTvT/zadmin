package com.z.service;


import com.z.entity.dto.AuthorityDto;
import com.z.entity.sys.SUser;

import java.util.List;
import java.util.Set;


public interface SRoleService {

    //获取用户所有权限
    List<AuthorityDto> getAuthorities(SUser user);
}
