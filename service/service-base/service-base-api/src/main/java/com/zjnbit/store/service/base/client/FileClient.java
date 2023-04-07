package com.zjnbit.store.service.base.client;

import com.zjnbit.store.framework.feign.config.ServiceServer;
import com.zjnbit.store.framework.web.model.Result;
import com.zjnbit.store.service.base.api.FileApiConst;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/21 17:03
 * @Description
 **/
@FeignClient(value = ServiceServer.SERVICE_BASE)
public interface FileClient {
    @GetMapping(value = FileApiConst.GET_WEB_URL + "/{id}")
    Result getWebUrl(@PathVariable("id") Long id);

    @PostMapping(value = FileApiConst.UPLOAD)
    Result upload(@RequestParam("file") MultipartFile file);
}
