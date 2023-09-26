package com.z.flowservice;


import com.z.bean.base.Response;
import com.z.bean.flowable.req.SaveBpmnXmlReq;
import com.z.bean.flowable.req.StartInstReq;

public interface FlowDefinitionService {
    void saveBpmnXml(SaveBpmnXmlReq req);

    Response startInst(StartInstReq req);
}
