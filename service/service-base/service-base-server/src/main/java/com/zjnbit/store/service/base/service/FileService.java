package com.zjnbit.store.service.base.service;

import com.zjnbit.store.framework.model.base.entity.SysUploadFileEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/21 09:18
 * @Description
 **/
public interface FileService {

    SysUploadFileEntity upload(MultipartFile file);

    String getWebUrl(Long fileId);

}
