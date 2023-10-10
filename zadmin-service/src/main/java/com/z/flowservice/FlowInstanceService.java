package com.z.flowservice;


import com.z.bean.base.Response;
import com.z.bean.flowable.req.StartInstReq;
import com.z.bean.flowable.req.UpdateInstStateReq;

public interface FlowInstanceService {

    Response updateInstState(UpdateInstStateReq req);
}
