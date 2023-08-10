package com.z.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.bean.admin.req.user.UserListReq;
import com.z.bean.admin.res.user.UserListRes;
import com.z.entity.sys.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SUserMapper extends BaseMapper<SUser> {

    List<String> getAllRoleByUserId(@Param("userId") Long id);

    IPage<UserListRes> getUserList(Page<UserListRes> page, @Param("req") UserListReq req);
}




