package com.z.flowservice.impl;


import com.z.bean.base.Response;
import com.z.bean.flowable.req.UpdateInstStateReq;
import com.z.flowservice.FlowInstanceService;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlowInstanceServiceImpl implements FlowInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public Response updateInstState(UpdateInstStateReq req) {
        if(Objects.equals(req.getUpdateType(),1)){
            runtimeService.activateProcessInstanceById(req.getProcInstId());
        }
        if(Objects.equals(req.getUpdateType(),2)){
            runtimeService.suspendProcessInstanceById(req.getProcInstId());
        }
        return Response.success();
    }
}
