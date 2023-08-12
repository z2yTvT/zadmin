package com.z.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.bean.admin.req.role.RoleByUidRes;
import com.z.bean.admin.req.role.RoleListReq;
import com.z.bean.admin.res.role.RoleListRes;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.dto.RoleMenuDto;
import com.z.entity.sys.SRole;
import com.z.entity.sys.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SRoleMapper extends BaseMapper<SRole> {

    List<AuthorityDto> selectRolePermByUserId(@Param("user") SUser user);

    IPage<RoleListRes> list(Page<RoleListRes> page, @Param("req") RoleListReq req);

    List<RoleMenuDto> selectMenusByRid(@Param("rid") String rid);

    List<RoleByUidRes> getRoleByUid(@Param("uid")Long uid);
}




