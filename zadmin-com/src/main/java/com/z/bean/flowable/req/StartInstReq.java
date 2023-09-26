package com.z.bean.flowable.req;

import lombok.Data;

import java.util.Map;

@Data
public class StartInstReq {

    private String procDefId;

    private Map<String,Object> variables;

}
