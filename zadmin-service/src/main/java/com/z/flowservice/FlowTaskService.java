package com.z.flowservice;


import com.z.bean.base.Response;
import com.z.bean.flowable.req.CompleteTaskReq;
import com.z.bean.flowable.req.DelegateReq;
import com.z.bean.flowable.req.RejectTaskReq;

public interface FlowTaskService {
    Response complete(CompleteTaskReq req);

    Response reject(RejectTaskReq req);

    Response delegate(DelegateReq req);
}
