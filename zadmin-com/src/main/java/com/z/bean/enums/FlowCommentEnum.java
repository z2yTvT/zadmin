package com.z.bean.enums;


public enum FlowCommentEnum {

    NORMAL("1", "正常"),
    REBACK("2", "退回"),
    REJECT("3", "驳回"),
    DELEGATE("4", "委派"),
    TRANSFER("5", "转办"),
    STOP("6", "终止"),
    REVOKE("7", "撤回");

    private final String type;

    private final String remark;

    FlowCommentEnum(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }

}
