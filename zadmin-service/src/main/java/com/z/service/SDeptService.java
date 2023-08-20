package com.z.service;


import com.z.bean.admin.req.dept.AddDeptReq;
import com.z.bean.admin.req.dept.DeptListReq;
import com.z.bean.admin.req.dept.EditDept;
import com.z.bean.base.Response;

public interface SDeptService{

    Response addDept(AddDeptReq req);

    Response editDept(EditDept req);

    Response getDeptTreeOpt();

    Response getDeptList(DeptListReq req);

    Response getDeptById(Long id);
}
