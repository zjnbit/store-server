package com.zjnbit.store.framework.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenjy
 * @emp chenjy
 * @mobile 18158571991
 * @date 2021/12/10 17:15
 * @Description
 **/
@Data
@ConfigurationProperties(prefix = "store.cache")
public class CacheProperties {
    private String address;
    private Integer database;
    private String password;
}
