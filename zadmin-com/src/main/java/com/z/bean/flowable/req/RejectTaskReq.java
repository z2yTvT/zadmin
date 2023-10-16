package com.z.bean.flowable.req;

import lombok.Data;

@Data
public class RejectTaskReq {

    private String taskId;

    private String comment;

    private String procInstId;
}
