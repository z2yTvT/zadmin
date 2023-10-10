package com.z.bean.flowable.res;

import lombok.Data;

@Data
public class DefListRes {

    /**
     * 流程定义id
     */
    private String id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 流程key
     */
    private String flowKey;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 流程分类
     */
    private String category;

    /**
     * 流程部署id
     */
    private String deploymentId;

    /**
     * 部署时间
     */
    private String deploymentTime;

    /**
     * 流程定义
     */
    private Integer suspensionState;

}
