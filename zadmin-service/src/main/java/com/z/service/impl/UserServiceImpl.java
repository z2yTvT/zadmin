package com.z.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.bean.admin.req.user.AddUserReq;
import com.z.bean.admin.req.user.EditUserReq;
import com.z.bean.admin.req.user.UserInfoRes;
import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.admin.req.user.UserListReq;
import com.z.bean.admin.res.User.UserListRes;
import com.z.bean.admin.res.role.RoleListRes;
import com.z.bean.base.Response;
import com.z.constant.SystemConstants;
import com.z.entity.dto.SecurityUserDto;
import com.z.entity.sys.SUser;
import com.z.service.SUserRoleService;
import com.z.service.SUserService;
import com.z.sys.mapper.SUserMapper;
import com.z.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private SUserRoleService userRoleService;


    @Override
    public Response editUser(EditUserReq req) {
        Integer count = userMapper.selectCount(new LambdaQueryWrapper<SUser>().eq(SUser::getUserName, req.getUserName()).ne(SUser::getId, req.getId()));
        if(count > 0){
            return Response.error("用户名已存在！");
        }
        SUser newUser = new SUser();
        BeanUtils.copyProperties(req,newUser);
        newUser.setUpdateTime(new Date());
        newUser.setUpdateUser(SecurityUtils.getSecurityUser().getUser().getUserName());
        userMapper.updateById(newUser);
        return Response.success();
    }

    @Override
    public Response getUserList(UserListReq req) {
        Page<UserListRes> page = new Page<>(req.getPageIndex(), req.getPageSize());
        IPage<UserListRes> pageList = userMapper.getUserList(page,req);
        return Response.success(pageList);
    }


    @Override
    public Response addUser(AddUserReq req) {
        LambdaQueryWrapper<SUser> ldw = new LambdaQueryWrapper<>();
        ldw.eq(SUser::getUserName,req.getUserName());
        Integer cnt = userMapper.selectCount(ldw);
        if(cnt > 0){
            return Response.error("用户已存在！");
        }
        String defaultPassword = SecurityUtils.encryptPassword(SystemConstants.DEFAULT_PASSWORD);
        SUser newUser = new SUser();
        BeanUtils.copyProperties(req,newUser);
        newUser.setUserType(2);
        newUser.setPassWord(defaultPassword);
        newUser.setCreateUser(SecurityUtils.getSecurityUser().getUser().getUserName());
        newUser.setCreateTime(new Date());
        newUser.setNickname(req.getUserName());
        long id =  userMapper.insert(newUser);
        Boolean res = userRoleService.addUserRoles(req.getRoleIds(), id);
        return Response.success(res);
    }

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
