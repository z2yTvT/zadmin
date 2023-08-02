package com.z.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.sys.SMenu;
import com.z.entity.sys.SMenuRole;
import com.z.entity.sys.SUser;
import com.z.service.SRoleService;
import com.z.sys.mapper.SMenuMapper;
import com.z.sys.mapper.SRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SRoleServiceImpl implements SRoleService {

    @Autowired
    private SRoleMapper roleMapper;

    @Override
    public List<AuthorityDto> getAuthorities(SUser user){
        HashSet<String> perms = new HashSet<>();
        if(user.getUserType().equals(1)){
            perms.add("admin");
            return perms.stream().map(AuthorityDto::new).collect(Collectors.toList());
        }
        List<AuthorityDto> authorities = roleMapper.selectRolePermByUserId(user);
        return authorities;
    }
}
