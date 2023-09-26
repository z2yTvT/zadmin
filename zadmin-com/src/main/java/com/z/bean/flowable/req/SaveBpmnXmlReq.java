package com.z.bean.flowable.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SaveBpmnXmlReq {

    private String name;

    private String key;

    private String category;

    @NotEmpty(message = "流程文件不能为空！")
    private String bpmnXml;

}
