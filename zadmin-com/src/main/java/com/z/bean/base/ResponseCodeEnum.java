package com.z.bean.base;


public enum ResponseCodeEnum {
    CODE_200("成功", "200"),
    CODE_400("参数非法", "400"),
    CODE_401("未登录", "401"),
    CODE_403("未授权", "403"),
    CODE_404("无效请求方法", "404"),
    CODE_500("系统异常,请稍后重试", "500");

    private String code;
    private String desc;

    private ResponseCodeEnum(String desc, String code) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
