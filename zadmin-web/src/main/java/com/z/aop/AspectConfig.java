package com.z.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(1)
@Slf4j
public class AspectConfig {

    @Pointcut("execution(* com.z.controller..*.*(..))")
    private void webLog(){
    }


    @Before(value="webLog()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("===========================================================================");
        log.info("请求日志的打印");
        log.info("请求地址:{}", Optional.ofNullable(request.getRequestURI().toString()).orElse(null));
        log.info("请求方式:{}",request.getMethod());
        log.info("请求类方法:{}",joinPoint.getSignature());
        log.info("请求类方法参数:{}", JSONObject.toJSONString(JSONObject.toJSONString(filterArgs(joinPoint.getArgs()))));
        log.info("===========================================================================");
    }

    private List<Object> filterArgs(Object[] objects) {
        return Arrays.stream(objects).filter(obj -> !(obj instanceof MultipartFile)
                && !(obj instanceof HttpServletResponse)
                && !(obj instanceof HttpServletRequest)).collect(Collectors.toList());
    }
}
