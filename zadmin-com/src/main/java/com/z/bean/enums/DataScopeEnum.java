package com.z.bean.enums;


public enum DataScopeEnum {

    DATA_SCOPE_All(4,"所有数据"),
    DATA_SCOPE_DEPT_BELOW(3,"本部门及一下数据"),
    DATA_SCOPE_DEPT(2,"本部门数据"),
    DATA_SCOPE_SELF(1,"本人数据");

    private Integer code;
    private String desc;

    private DataScopeEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

}
