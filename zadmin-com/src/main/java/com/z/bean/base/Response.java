package com.z.bean.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Response<T>{

    private String code;
    private String message;
    private T data;

    public static Response success() {
        return set("200");
    }

    public static Response success(Object data) {
        return set("200", data);
    }

    public static Response error(String message){
        return set("400", message, null);
    }


    public static Response set(String code, String desc, Object data) {
        return new Response(code, desc, data);
    }

    private static Response set(ResponseCodeEnum responseCode, Object data) {
        return new Response(responseCode.getCode(), responseCode.getDesc(), data);
    }

    public static Response set(String code, Object data) {
        return new Response(code, data);
    }
    public static Response set(String code) {
        return new Response(code);
    }


    public Response(String code, String desc, T data){
        this.code = code;
        this.message = desc;
        this.data = data;
    }

    public Response(String code, T data){
        this.code = code;
        this.data = data;
    }

    public Response(String code){
        this.code = code;
    }

}