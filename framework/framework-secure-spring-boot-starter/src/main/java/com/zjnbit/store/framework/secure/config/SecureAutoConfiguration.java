package com.zjnbit.store.framework.secure.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/24 08:58
 * @Description
 **/
@Configuration
@EnableConfigurationProperties(SecureProperties.class)
public class SecureAutoConfiguration {

    private SecureProperties properties;

    public SecureAutoConfiguration(SecureProperties secureProperties) {
        this.properties = secureProperties;
    }

    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName("authorization");             // token名称 (同时也是cookie名称)
        config.setIsPrint(false);                        //是否在初始化配置时打印版本字符画
        config.setJwtSecretKey(properties.getJwtSecretKey());
        config.setTokenPrefix("Bearer ");                // token前缀
        return config;
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }
}
