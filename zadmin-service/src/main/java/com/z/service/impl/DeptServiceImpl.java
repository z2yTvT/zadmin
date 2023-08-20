package com.z.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.z.bean.admin.req.dept.AddDeptReq;
import com.z.bean.admin.req.dept.DeptListReq;
import com.z.bean.admin.req.dept.EditDept;
import com.z.bean.base.Response;
import com.z.constant.SystemConstants;
import com.z.entity.sys.SDept;
import com.z.entity.vo.DeptOptVO;
import com.z.entity.vo.DeptVo;
import com.z.service.SDeptService;
import com.z.sys.mapper.SDeptMapper;
import com.z.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DeptServiceImpl implements SDeptService {

    @Autowired
    private SDeptMapper deptMapper;


    @Override
    public Response getDeptById(Long id) {
        if(id == null){
            return Response.error("部门id不能为空！");
        }
        SDept dept = deptMapper.selectOne(new LambdaQueryWrapper<SDept>().eq(SDept::getDeleted, 0).eq(SDept::getId,id));
        if(dept == null){
            return Response.error("部门不存在！");
        }
        return Response.success(dept);
    }


    @Override
    public Response getDeptList(DeptListReq req) {
        List<SDept> allDept = deptMapper.selectList(new LambdaQueryWrapper<SDept>().eq(SDept::getDeleted, 0));
        List<DeptVo> res = recurDept2ListVo(SystemConstants.DEPT_PARENT_ID,allDept);
        return Response.success(res);
    }

    private List<DeptVo> recurDept2ListVo(Long deptParentId, List<SDept> allDept) {
        List<DeptVo> res = new ArrayList<>();
        res = allDept.stream().filter(a -> a.getPId().equals(deptParentId))
                .map(a -> {
                    DeptVo deptVo = new DeptVo();
                    BeanUtils.copyProperties(a,deptVo);
                    deptVo.setChildren(recurDept2ListVo(a.getId(),allDept));
                    return deptVo;
                }).collect(Collectors.toList());
        return res;
    }

    @Override
    public Response getDeptTreeOpt() {
        List<SDept> allDept = deptMapper.selectList(new LambdaQueryWrapper<SDept>().eq(SDept::getDeleted, 0));
        List<DeptOptVO> res = recurDept2OptTree(SystemConstants.DEPT_PARENT_ID,allDept);
        return Response.success(res);
    }

    private List<DeptOptVO> recurDept2OptTree(Long pid, List<SDept> allDept) {
        List<DeptOptVO> res = new ArrayList<>();
        res = allDept.stream().filter(a -> a.getPId().equals(pid))
                .map(a -> {
                    DeptOptVO deptOptVO = new DeptOptVO();
                    BeanUtils.copyProperties(a,deptOptVO);
                    deptOptVO.setLabel(a.getDeptName());
                    deptOptVO.setChildren(recurDept2OptTree(a.getId(),allDept));
                    return deptOptVO;
                }).collect(Collectors.toList());
        return res;
    }

    @Override
    public Response editDept(EditDept req) {
        Integer count = deptMapper.selectCount(new LambdaQueryWrapper<SDept>().eq(SDept::getId, req.getId()));
        if(count == 0){
            return Response.error("部门不存在");
        }
        SDept newDept = new SDept();
        BeanUtils.copyProperties(req,newDept);
        newDept.setUpdateTime(new Date());
        newDept.setUpdateUser(SecurityUtils.getSecurityUser().getUser().getUserName());
        deptMapper.updateById(newDept);
        return Response.success();
    }

    @Override
    public Response addDept(AddDeptReq req) {
        Integer count = deptMapper.selectCount(new LambdaQueryWrapper<SDept>()
                .eq(SDept::getDeptName, req.getDeptName())
                .eq(SDept::getDeleted, 0));
        if(count > 0){
            return Response.error("部门名称已存在！");
        }
        SDept newDept = new SDept();
        BeanUtils.copyProperties(req,newDept);
        newDept.setCreateTime(new Date());
        newDept.setCreateUser(SecurityUtils.getSecurityUser().getUser().getUserName());
        deptMapper.insert(newDept);
        return Response.success();
    }
}




