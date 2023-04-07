package com.zjnbit.store.service.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/21 15:56
 * @Description
 **/
@Data
@ConfigurationProperties(prefix = "store.file")
public class FileProperties {
    private String apiUrl;
    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String cdnHost;
}
