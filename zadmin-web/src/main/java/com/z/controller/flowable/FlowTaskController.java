package com.z.controller.flowable;

import com.z.bean.base.Response;
import com.z.bean.flowable.req.CompleteTaskReq;
import com.z.flowservice.FlowTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/flow/task")
public class FlowTaskController {

    @Autowired
    private FlowTaskService flowTaskService;

    @PostMapping("/complete")
    public Response complete(@RequestBody @Valid CompleteTaskReq req){
        return flowTaskService.complete(req);
    }

}
