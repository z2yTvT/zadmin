package com.z.aop;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.z.annotation.NoRepeatSubmit;
import com.z.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Configuration
@Aspect
@RequiredArgsConstructor
public class NoRepeatSubmitAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(com.z.annotation.NoRepeatSubmit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String sigStr = signature.toString();

        Object[] args = pjp.getArgs();
        String argStr = combineArgs(args);
        String hash = SecureUtil.md5(sigStr + argStr);

        NoRepeatSubmit ann = signature.getMethod().getAnnotation(NoRepeatSubmit.class);
        int interval = ann.interval();
        RLock lock = redissonClient.getLock(hash);
        try {
            boolean isLocked = lock.tryLock(1, interval, TimeUnit.SECONDS);
            if (!isLocked) {
                throw new ServiceException("请勿重复提交", "500");
            }
        } catch (InterruptedException e) {
            throw new ServiceException(e,"500");
        }
        return pjp.proceed();
    }

    private String combineArgs(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                continue;
            }
            sb.append(JSON.toJSONString(arg));
        }
        return sb.toString();
    }
}