package com.z.bean.flowable.req;

import lombok.Data;

@Data
public class DefStateReq {

    /**
     * 1:激活 2:挂起
     */
    private Integer updateType;

    private String defId;

}
