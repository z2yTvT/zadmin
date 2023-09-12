package com.z.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.z.entity.sys.SUser;
import com.z.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(1)
@Slf4j
public class InitDataAspect {

    @Pointcut("execution(* com.z.controller..*.*(..))")
    private void webLog(){
    }

    @Before("webLog()")
    public void before() {
        log.info("用户请求");
//        SUser user = SecurityUtils.getSecurityUser().getUser();
//        log.info(JSONObject.toJSONString(user));

    }


    @Around(value="webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        Map<String, Object> args =  parseArgs(pjp);
        log.info(String.format("<%s><%s><%s>%s", className, methodName, "Request", JSON.toJSONString(args)));
        Object obj = pjp.proceed();
        log.info(String.format("<%s><%s><%s>%s", className, methodName, "Response", JSON.toJSONString(obj)));
        return obj;
    }

    private Map<String, Object> parseArgs(ProceedingJoinPoint pjp) {
        Map<String,Object> paramMap = new HashMap<>();

        Object[] args = pjp.getArgs();//参数对象
        String[] parameterNames = ((CodeSignature) pjp.getSignature()).getParameterNames();//参数名
        Class[] classes = ((CodeSignature) pjp.getSignature()).getParameterTypes();//参数class对象

        for(int i = 0;i < args.length;i ++){
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile || args[i] instanceof BindingResult) {
                continue;
            }
            String simpleName = classes[i].getSimpleName();
            if (simpleName.equalsIgnoreCase("MultipartFile[]") ||
                    simpleName.equalsIgnoreCase("MultipartFile")) {
                continue;
            }
            paramMap.put(parameterNames[i], args[i]);
        }

        return paramMap;
    }

}
