package com.zjnbit.store.framework.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/21 15:40
 * @Description
 **/
public abstract class BaseController<S> {

    @Autowired
    protected S baseService;


}
