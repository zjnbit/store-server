package com.zjnbit.store.api.web.controller.common;

import com.zjnbit.store.framework.web.model.Result;
import com.zjnbit.store.service.base.client.FileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/21 17:30
 * @Description
 **/
@RestController
public class FileController {

    @Autowired
    FileClient fileClient;

    @GetMapping("/test/{id}")
    public Result test(@PathVariable("id") Long id) {
        return fileClient.getWebUrl(id);
    }
}
