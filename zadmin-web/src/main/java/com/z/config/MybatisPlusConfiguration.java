package com.z.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfiguration {

    /**
     * mybatis-plus 分页插件
     *
     * @return
     */
    @Bean(name = "mybatisPlusInterceptor")
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptors = new MybatisPlusInterceptor();
        PaginationInnerInterceptor page = new PaginationInnerInterceptor(DbType.MYSQL);
        interceptors.addInnerInterceptor(page);
        interceptors.addInnerInterceptor(new DataPermissionInterceptor(new DataPermissionHandlerImpl()));
        return interceptors;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaObjectHandlerImpl());
        return globalConfig;
    }

}
