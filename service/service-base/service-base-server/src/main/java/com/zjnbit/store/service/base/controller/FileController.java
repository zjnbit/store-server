package com.zjnbit.store.service.base.controller;

import com.zjnbit.store.framework.web.controller.BaseController;
import com.zjnbit.store.framework.web.model.Result;
import com.zjnbit.store.service.base.api.FileApiConst;
import com.zjnbit.store.service.base.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/21 15:30
 * @Description
 **/
@RestController
public class FileController extends BaseController<FileService> {


    @GetMapping(value = FileApiConst.GET_WEB_URL + "/{id}")
    public Result getWebUrl(@PathVariable("id") Long id) {
        return Result.success(baseService.getWebUrl(id));
    }

    @PostMapping(value = FileApiConst.UPLOAD)
    public Result upload(@RequestParam("file") MultipartFile file) {
        return Result.success(baseService.upload(file));
    }

}
