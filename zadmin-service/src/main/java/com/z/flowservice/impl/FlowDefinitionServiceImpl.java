package com.z.flowservice.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.bean.base.Response;
import com.z.bean.flowable.req.*;
import com.z.bean.flowable.res.DefListRes;
import com.z.constant.SystemConstants;
import com.z.exception.ServiceException;
import com.z.flowservice.FlowDefinitionService;
import com.z.sys.mapper.FlowDeployMapper;
import com.z.utils.SecurityUtils;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.P;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.engine.RepositoryService;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Objects;


@Service
public class FlowDefinitionServiceImpl implements FlowDefinitionService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private FlowDeployMapper flowDeployMapper;

    @Override
    public Response startInst(StartInstReq req) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(req.getProcDefId())
                .singleResult();
        if (Objects.isNull(processDefinition)) {
            return Response.error("该流程不存在！");
        }
        if (processDefinition.isSuspended()) {
            return Response.error("该流程已挂起，请先激活流程");
        }
        Map<String, Object> variables = req.getVariables();
        String initiator = String.valueOf(SecurityUtils.getSecurityUser().getUser().getId());
        variables.put(BpmnXMLConstants.ATTRIBUTE_EVENT_START_INITIATOR, initiator);
        variables.put("_FLOWABLE_SKIP_EXPRESSION_ENABLED", true);
        runtimeService.startProcessInstanceById(req.getProcDefId(), variables);
        return Response.success("启动流程成功");
    }

    @Override
    public void saveBpmnXml(SaveBpmnXmlReq req) {
        try (InputStream is = new ByteArrayInputStream(req.getBpmnXml().getBytes(StandardCharsets.UTF_8))) {
            repositoryService.createDeployment()
                    .addInputStream(req.getName() + SystemConstants.BPMN_FILE_SUFFIX, is)
                    .name(req.getName())
                    .category(req.getCategory()).deploy();
        } catch (IOException e) {
            throw new ServiceException(e, "500");
        }
    }

    @Override
    public Response updateDefState(DefStateReq req) {
        if (req.getUpdateType().equals(1)) {
            repositoryService.activateProcessDefinitionById(req.getDefId(), true, new Date());
        }
        if (req.getUpdateType().equals(2)) {
            repositoryService.suspendProcessDefinitionById(req.getDefId(), true, new Date());
        }
        return Response.success();
    }

    @Override
    public Response delDeploy(DelDeployReq req) {
        repositoryService.deleteDeployment(req.getDeployId(), true);
        return Response.success();
    }

    @Override
    public Response list(DefListReq req) {
        Page<DefListRes> page = new Page<>(req.getPageIndex(), req.getPageSize());
        IPage<DefListRes> pageList = flowDeployMapper.selectDeployList(page, req);
        return Response.success(pageList);
    }
}
