package com.z.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解防止接口重复提交
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit
{
    /**
     * 间隔时间(s)
     */
    int interval() default 1;

    /**
     * 提示消息
     */
    String message() default "不允许重复提交，请稍后再试";
}