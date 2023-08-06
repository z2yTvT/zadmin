package com.z.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.bean.admin.req.role.RoleAddReq;
import com.z.bean.admin.req.role.RoleEditReq;
import com.z.bean.admin.req.role.RoleListReq;
import com.z.bean.admin.res.role.RoleListRes;
import com.z.bean.base.Response;
import com.z.entity.dto.AuthorityDto;
import com.z.entity.sys.SMenu;
import com.z.entity.sys.SMenuRole;
import com.z.entity.sys.SRole;
import com.z.entity.sys.SUser;
import com.z.service.SRoleService;
import com.z.sys.mapper.SMenuMapper;
import com.z.sys.mapper.SRoleMapper;
import com.z.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements SRoleService {

    @Autowired
    private SRoleMapper roleMapper;

    @Override
    public Response edit(RoleEditReq req) {
        LambdaQueryWrapper<SRole> ldw = new LambdaQueryWrapper<>();
        ldw.ne(req.getId() != null,SRole::getId,req.getId())
                .and(w -> w.eq(SRole::getRoleKey,req.getRoleKey())
                                .or()
                                .eq(SRole::getRoleName,req.getRoleName()));
        Integer cntDb = roleMapper.selectCount(ldw);
        if(cntDb > 0){
            return Response.error("角色名称或角色权限标识已存在！");
        }
        SRole newRole = new SRole();
        BeanUtils.copyProperties(req,newRole);
        newRole.setCreateTime(new Date());
        newRole.setCreateUser(SecurityUtils.getSecurityUser().getUser().getUserName());
        roleMapper.updateById(newRole);
        return Response.success();
    }

    public Response list(RoleListReq req){
        Page<RoleListRes> page = new Page<>(req.getPageIndex(),req.getPageSize());
        IPage<RoleListRes> pageList = roleMapper.list(page,req);
        return Response.success(pageList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> addRole(RoleAddReq req){
        if(StrUtil.isBlank(req.getRoleKey()) || StrUtil.isBlank(req.getRoleName())){
            return Response.error("角色名称标识不能为空！");
        }
        LambdaQueryWrapper<SRole> ldw = new LambdaQueryWrapper<>();
        ldw.eq(SRole::getRoleName,req.getRoleName());
        SRole roleDb = roleMapper.selectOne(ldw);
        if(roleDb != null){
            return Response.error("角色已存在！");
        }
        SRole newRole= new SRole();
        newRole.setCreateUser(SecurityUtils.getSecurityUser().getUsername());
        newRole.setCreateTime(new Date());
        BeanUtils.copyProperties(req,newRole);
        roleMapper.insert(newRole);
        return Response.set("200","success",null);
    }

    @Override
    public List<AuthorityDto> getAuthorities(SUser user){
        HashSet<String> perms = new HashSet<>();
        if(user.getUserType().equals(1)){
            perms.add("admin");
            return perms.stream().map(AuthorityDto::new).collect(Collectors.toList());
        }
        List<AuthorityDto> authorities = roleMapper.selectRolePermByUserId(user);
        return authorities;
    }
}
