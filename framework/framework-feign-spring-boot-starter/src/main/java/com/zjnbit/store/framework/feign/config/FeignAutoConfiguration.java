package com.zjnbit.store.framework.feign.config;

import com.zjnbit.store.framework.feign.interceptor.FeignInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenjy
 * @emp chenjy 
 * @mobile 18158571991
 * @date 2021/12/6 08:52
 * @Description
 **/
@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.zjnbit.store")
public class FeignAutoConfiguration {

    @Bean
    public FeignInterceptor feignInterceptor() {
        log.info("加载feign拦截器");
        return new FeignInterceptor();
    }

}
