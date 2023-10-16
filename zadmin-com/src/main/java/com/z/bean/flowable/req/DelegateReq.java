package com.z.bean.flowable.req;

import lombok.Data;

@Data
public class DelegateReq {

    private String taskId;

    private Long userId;

    private String userName;

    private String comment;

}
