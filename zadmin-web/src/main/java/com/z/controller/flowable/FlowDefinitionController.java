package com.z.controller.flowable;

import com.z.bean.base.Response;
import com.z.bean.flowable.req.SaveBpmnXmlReq;
import com.z.bean.flowable.req.StartInstReq;
import com.z.flowservice.FlowDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/flow/def")
public class FlowDefinitionController {

    @Autowired
    private FlowDefinitionService flowDefinitionService;

    @PostMapping("/saveBpmnXml")
    public Response saveBpmnXml(@RequestBody @Valid SaveBpmnXmlReq req){
        flowDefinitionService.saveBpmnXml(req);
        return Response.success();
    }

    @PostMapping("/startInst")
    public Response startInst(@RequestBody StartInstReq req){
        return flowDefinitionService.startInst(req);
    }

}
