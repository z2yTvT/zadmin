package com.z.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.z.bean.admin.req.user.UserInfoRes;
import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.base.Response;
import com.z.entity.dto.SecurityUserDto;
import com.z.entity.sys.SUser;
import com.z.service.SUserService;
import com.z.sys.mapper.SUserMapper;
import com.z.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements SUserService {

    @Autowired
    private SUserMapper userMapper;

    public Response<UserInfoRes> getUserInfo(){
        SecurityUserDto securityUser = SecurityUtils.getSecurityUser();
        SUser user = securityUser.getUser();

        List<String> perms = securityUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<String> roles =  userMapper.getAllRoleByUserId(user.getId());

        UserInfoRes userInfoRes = new UserInfoRes();
        userInfoRes.setName(user.getNickname());
        userInfoRes.setAvatar(user.getAvatar());
        userInfoRes.setPerms(perms);
        userInfoRes.setRoles(roles);

        return Response.success(userInfoRes);
    }

    public Response<Boolean> register(LoginReq req) {

        LambdaQueryWrapper<SUser> ldw = new LambdaQueryWrapper<>();
        ldw.eq(SUser::getUserName,req.getUsername());
        SUser userDb = userMapper.selectOne(ldw);
        if(userDb != null){
            return Response.error("用户名已存在");
        }

        SUser user = new SUser();
        String password = req.getPassword();
        String username = req.getUsername();

        user.setNickname(username);
        user.setPassWord(SecurityUtils.encryptPassword(password));
        user.setUserName(username);
        user.setCreateTime(new Date());
        user.setCreateUser(username);
        user.setUserType(2);

        userMapper.insert(user);
        return Response.success();
    }
}
