package com.z.flowservice.impl;


import com.z.bean.base.Response;
import com.z.bean.enums.FlowCommentEnum;
import com.z.bean.flowable.req.CompleteTaskReq;
import com.z.flowservice.FlowTaskService;
import com.z.utils.SecurityUtils;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FlowTaskServiceImpl implements FlowTaskService {

    @Autowired
    private TaskService taskService;

    @Override
    public Response complete(CompleteTaskReq req) {
        Task task = taskService.createTaskQuery().taskId(req.getTaskId()).singleResult();
        if (task == null) {
            return Response.error("该任务不存在！");
        }
        taskService.addComment(req.getTaskId(), FlowCommentEnum.NORMAL.getType(), req.getComment());
        String userId = String.valueOf(SecurityUtils.getSecurityUser().getUser().getId());
        taskService.setAssignee(req.getTaskId(), userId);
        taskService.complete(req.getTaskId(), req.getVariables());

        return Response.success();

    }
}
