package com.z.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.dto.SecurityUserDto;
import com.z.entity.sys.SUser;
import com.z.service.SRoleService;
import com.z.sys.mapper.SUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:zadmin
 * @author: Zzz
 * @Time: 2023/8/1  9:29
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SUserMapper userMapper;

    @Autowired
    private SRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StrUtil.isBlank(username)){
            throw  new RuntimeException("用户名不能为空");
        }
        LambdaQueryWrapper<SUser> ldw = new LambdaQueryWrapper<>();
        ldw.eq(SUser::getUserName,username);
        SUser user = userMapper.selectOne(ldw);
        if(user != null){
            List<AuthorityDto> authorities = roleService.getAuthorities(user);
            return new SecurityUserDto(user,authorities);//todo 权限
        }
        throw new UsernameNotFoundException("user not found!");
    }
}
