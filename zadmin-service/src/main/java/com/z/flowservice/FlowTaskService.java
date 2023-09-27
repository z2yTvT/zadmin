package com.z.flowservice;


import com.z.bean.base.Response;
import com.z.bean.flowable.req.CompleteTaskReq;

public interface FlowTaskService {
    Response complete(CompleteTaskReq req);
}
