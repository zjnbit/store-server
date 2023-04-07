package com.zjnbit.store.service.base.config;

import lombok.Data;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/21 16:05
 * @Description
 **/
@Data
public class FileUploadInfo {
    private Long id;
    private String filePath;
    private String webUrl;
    private String contentType;
    private Long size;
}
