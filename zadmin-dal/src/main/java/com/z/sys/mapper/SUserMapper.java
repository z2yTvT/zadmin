package com.z.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z.entity.sys.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SUserMapper extends BaseMapper<SUser> {

    List<String> getAllRoleByUserId(@Param("userId") Long id);
}




