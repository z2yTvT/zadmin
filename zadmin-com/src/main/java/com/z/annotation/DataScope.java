package com.z.annotation;


import com.z.bean.enums.DataScopeEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataScope {

    String deptAlias() default "";

    String userAlias() default "";

    DataScopeEnum dataScopeEnum() default DataScopeEnum.DATA_SCOPE_DEPT_BELOW;
}
