package com.zjnbit.store.service.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/21 15:55
 * @Description
 **/
@Configuration
@EnableConfigurationProperties(FileProperties.class)
public class FileConfiguration {

    private FileProperties fileProperties;

    public FileConfiguration(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
    }


    @Bean
    @ConditionalOnMissingBean(S3Client.class)
    public S3Client s3Client(FileProperties fileProperties) {
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(fileProperties.getAccessKeyId(), fileProperties.getAccessKeySecret())))
                .region(Region.of(fileProperties.getRegion()))
                .endpointOverride(URI.create(fileProperties.getApiUrl()))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(false)
                        .chunkedEncodingEnabled(false)
                        .build())
                .build();
    }

    @Bean
    @ConditionalOnBean({S3Client.class})
    @ConditionalOnMissingBean(FileTemplate.class)
    public FileTemplate fileTemplate(S3Client s3Client) {
        return new FileTemplate(s3Client, fileProperties);
    }
}
