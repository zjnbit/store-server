package com.zjnbit.store.service.base.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.zjnbit.store.framework.common.constant.DatePatternConst;
import com.zjnbit.store.framework.common.constant.StringPool;
import com.zjnbit.store.framework.web.model.RequestError;
import com.zjnbit.store.framework.web.model.RequestException;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/21 16:00
 * @Description
 **/
public class FileTemplate {
    private S3Client s3Client;
    private FileProperties fileProperties;

    public FileTemplate(S3Client s3Client, FileProperties fileProperties) {
        this.s3Client = s3Client;
        this.fileProperties = fileProperties;
    }

    @SneakyThrows
    public FileUploadInfo upload(MultipartFile file) {
        FileUploadInfo fileUploadInfo = new FileUploadInfo();
        fileUploadInfo.setId(IdUtil.getSnowflakeNextId());
        fileUploadInfo.setFilePath(DateUtil.format(DateUtil.date(), DatePatternConst.PATTERN_DATE_MINI) + "/" + fileUploadInfo.getId() + StringPool.DOT + FileUtil.extName(file.getOriginalFilename()));
        fileUploadInfo.setWebUrl(fileProperties.getCdnHost() + "/" + fileUploadInfo.getFilePath());
        fileUploadInfo.setContentType(file.getContentType());
        fileUploadInfo.setSize(file.getSize());
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(fileProperties.getBucketName())
                .key(fileUploadInfo.getFilePath())
                .contentType(file.getContentType())
                .build();
        PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        if (response.sdkHttpResponse().isSuccessful()) {
            return fileUploadInfo;
        } else {
            throw new RequestException(RequestError.C0101);
        }
    }
}
