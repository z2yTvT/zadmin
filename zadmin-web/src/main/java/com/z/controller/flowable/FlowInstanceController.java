package com.z.controller.flowable;

import com.z.bean.base.Response;
import com.z.bean.flowable.req.StartInstReq;
import com.z.bean.flowable.req.UpdateInstStateReq;
import com.z.flowservice.FlowInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow/inst")
public class FlowInstanceController {

    @Autowired
    private FlowInstanceService flowInstanceService;

    @PostMapping("/updateInstState")
    public Response updateInstState(@RequestBody UpdateInstStateReq req){
        return flowInstanceService.updateInstState(req);
    }

}
