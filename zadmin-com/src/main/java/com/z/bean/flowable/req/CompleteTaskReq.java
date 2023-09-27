package com.z.bean.flowable.req;

import lombok.Data;

import java.util.Map;

@Data
public class CompleteTaskReq {

    private String taskId;

    private String comment;

    private String procInstId;

    private Map<String,Object> variables;

}
