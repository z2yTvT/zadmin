package com.z.flowservice.impl;


import com.z.bean.base.Response;
import com.z.bean.enums.FlowCommentEnum;
import com.z.bean.flowable.req.CompleteTaskReq;
import com.z.bean.flowable.req.DelegateReq;
import com.z.bean.flowable.req.RejectTaskReq;
import com.z.flowservice.FlowTaskService;
import com.z.utils.SecurityUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlowTaskServiceImpl implements FlowTaskService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response delegate(DelegateReq req) {
        Task task = taskService.createTaskQuery().taskId(req.getTaskId()).singleResult();
        if(task == null){
            return Response.error("任务不存在！");
        }
        taskService.addComment(req.getTaskId(), task.getProcessInstanceId(),FlowCommentEnum.REJECT.getType(),req.getComment());
        taskService.delegateTask(req.getTaskId(),String.valueOf(req.getUserId()));
        taskService.setOwner(req.getTaskId(),String.valueOf(SecurityUtils.getSecurityUser().getUser().getId()));
        return Response.success();
    }

    @Override
    public Response reject(RejectTaskReq req) {
        Task task = taskService.createTaskQuery().taskId(req.getTaskId()).singleResult();
        if(task == null){
            return Response.error("任务不存在！");
        }
        if(task.isSuspended()){
            return Response.error("任务处于挂起中！");
        }
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(req.getProcInstId()).singleResult();
        if(processInstance  == null){
            return Response.error("任务流程实例不存在！");
        }
        taskService.addComment(req.getTaskId(),processInstance.getProcessInstanceId(),FlowCommentEnum.REJECT.getType(),req.getComment());
        return Response.success();
    }

    @Override
    public Response complete(CompleteTaskReq req) {
        Task task = taskService.createTaskQuery().taskId(req.getTaskId()).singleResult();
        if (task == null) {
            return Response.error("该任务不存在！");
        }
        if(task.isSuspended()){
            return Response.error("任务处于挂起中！");
        }
        taskService.addComment(req.getTaskId(),req.getProcInstId(), FlowCommentEnum.NORMAL.getType(), req.getComment());
        String userId = String.valueOf(SecurityUtils.getSecurityUser().getUser().getId());
        taskService.setAssignee(req.getTaskId(), userId);
        taskService.complete(req.getTaskId(), req.getVariables());

        return Response.success();

    }
}
