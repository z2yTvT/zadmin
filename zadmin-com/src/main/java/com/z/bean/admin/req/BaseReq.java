package com.z.bean.admin.req;

import lombok.Data;

@Data
public class BaseReq {
    /**
     * 当前页码
     */
    private Integer pageIndex = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;
}
