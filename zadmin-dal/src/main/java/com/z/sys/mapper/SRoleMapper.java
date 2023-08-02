package com.z.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.sys.SRole;
import com.z.entity.sys.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SRoleMapper extends BaseMapper<SRole> {

    List<AuthorityDto> selectRolePermByUserId(@Param("user") SUser user);

}




