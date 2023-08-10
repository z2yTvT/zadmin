package com.z.sys.mapper;

import com.z.entity.sys.SUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SUserRoleMapper extends BaseMapper<SUserRole> {

    void deleteUserRole(@Param("uid") Long uid, @Param("rids") List<Long> roleIds);
}




