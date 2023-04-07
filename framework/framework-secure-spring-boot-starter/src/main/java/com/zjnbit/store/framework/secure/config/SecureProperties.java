package com.zjnbit.store.framework.secure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/24 09:14
 * @Description
 **/
@Data
@ConfigurationProperties("store.secure")
public class SecureProperties {
    private String jwtSecretKey;
}
