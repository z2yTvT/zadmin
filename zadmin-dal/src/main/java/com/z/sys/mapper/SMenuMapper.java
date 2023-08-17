package com.z.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z.entity.dto.RouteDto;
import com.z.entity.sys.SMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface SMenuMapper extends BaseMapper<SMenu> {

    List<RouteDto> getAllRoutes(@Param("withBtn")Integer withBtn);

    List<Long> getSelectedMenus(@Param("rid") Long rid);
}




