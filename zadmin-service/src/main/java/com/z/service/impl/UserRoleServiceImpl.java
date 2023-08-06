package com.z.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.z.entity.sys.SUserRole;
import com.z.service.SUserRoleService;
import com.z.sys.mapper.SUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class UserRoleServiceImpl implements SUserRoleService {

    @Autowired
    private SUserRoleMapper userRoleMapper;

    @Override
    public Boolean addUserRoles(List<Long> roleIds, Long userId) {
        if(CollUtil.isEmpty(roleIds) || userId == null){
            return Boolean.FALSE;
        }
        roleIds.forEach(rid ->{
            SUserRole userRole = new SUserRole();
            userRole.setRId(rid);
            userRole.setUId(userId);
        });
        return Boolean.TRUE;
    }
}




