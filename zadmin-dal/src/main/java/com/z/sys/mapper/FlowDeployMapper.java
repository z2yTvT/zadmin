package com.z.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.bean.flowable.req.DefListReq;
import com.z.bean.flowable.res.DefListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlowDeployMapper {
    IPage<DefListRes> selectDeployList(Page<DefListRes> page, @Param("req") DefListReq req);
}