package com.zjnbit.store.service.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zjnbit.store.framework.cache.constant.CacheNameConst;
import com.zjnbit.store.framework.model.base.entity.SysUploadFileEntity;
import com.zjnbit.store.framework.web.model.RequestError;
import com.zjnbit.store.framework.web.model.RequestException;
import com.zjnbit.store.service.base.config.FileTemplate;
import com.zjnbit.store.service.base.config.FileUploadInfo;
import com.zjnbit.store.service.base.mapper.SysUploadFileMapper;
import com.zjnbit.store.service.base.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/21 09:20
 * @Description
 **/
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileTemplate fileTemplate;
    @Autowired
    private SysUploadFileMapper sysUploadFileMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public SysUploadFileEntity upload(MultipartFile file) {
        FileUploadInfo fileUploadInfo = fileTemplate.upload(file);
        SysUploadFileEntity sysUploadFileEntity = BeanUtil.copyProperties(fileUploadInfo, SysUploadFileEntity.class);
        sysUploadFileMapper.insert(sysUploadFileEntity);
        return sysUploadFileEntity;
    }

    @Override
    public String getWebUrl(Long fileId) {
        if (redisTemplate.hasKey(CacheNameConst.FILE_URL + fileId)) {
            return (String) redisTemplate.opsForValue().get(CacheNameConst.FILE_URL + fileId);
        }
        SysUploadFileEntity sysUploadFileEntity = sysUploadFileMapper.selectById(fileId);
        if (null == sysUploadFileEntity) {
            throw new RequestException(RequestError.A0002);
        }
        redisTemplate.opsForValue().set(CacheNameConst.FILE_URL + fileId, sysUploadFileEntity.getWebUrl(), 1, TimeUnit.DAYS);
        return sysUploadFileEntity.getWebUrl();
    }
}
