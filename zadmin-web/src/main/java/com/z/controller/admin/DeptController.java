package com.z.controller.admin;

import com.z.bean.admin.req.dept.AddDeptReq;
import com.z.bean.admin.req.dept.DeptListReq;
import com.z.bean.admin.req.dept.EditDept;
import com.z.bean.base.Response;
import com.z.service.SDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private SDeptService deptService;

    @GetMapping("getDeptById")
    public Response getDeptById(@RequestParam Long id){
        return deptService.getDeptById(id);
    }

    @PostMapping("getDeptList")
    public Response getDeptList(@RequestBody DeptListReq req){
        return deptService.getDeptList(req);
    }

    @GetMapping("/getDeptTreeOpt")
    public Response getDeptTreeOpt(){
        return deptService.getDeptTreeOpt();
    }

    @PostMapping("/editDept")
    public Response editDept(@RequestBody @Valid EditDept req){
        return deptService.editDept(req);
    }

    @PostMapping("/addDept")
    public Response addDept(@RequestBody @Valid AddDeptReq req){
        return deptService.addDept(req);
    }

}
