package com.z.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.z.bean.admin.req.login.LoginReq;
import com.z.bean.base.Response;
import com.z.entity.sys.SUser;
import com.z.service.SUserService;
import com.z.sys.mapper.SUserMapper;
import com.z.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

/**
 * @program:zadmin
 * @author: Zzz
 * @Time: 2023/8/1  18:04
 */
@Service
public class UserService implements SUserService {

    @Autowired
    private SUserMapper userMapper;

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
