package com.z.flowservice;


import com.z.bean.base.Response;
import com.z.bean.flowable.req.*;

public interface FlowDefinitionService {
    void saveBpmnXml(SaveBpmnXmlReq req);

    Response startInst(StartInstReq req);

    Response updateDefState(DefStateReq req);

    Response delDeploy(DelDeployReq req);

    Response list(DefListReq req);
}
