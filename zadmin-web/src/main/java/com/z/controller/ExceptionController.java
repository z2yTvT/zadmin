package com.z.controller;

import com.z.bean.base.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionController {

//    @ExceptionHandler(value = {Exception.class})//todo 异常细化
//    @ResponseBody
//    public Response handleException(Exception e){
//        log.error("==========================异常===========================");
//        log.error(e.getMessage());
//        e.printStackTrace();
//        log.error("==========================异常===========================");
//        return Response.set("500","服务器异常",null);
//    }
}
