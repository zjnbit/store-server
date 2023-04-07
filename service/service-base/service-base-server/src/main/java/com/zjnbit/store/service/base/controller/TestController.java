package com.zjnbit.store.service.base.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.zjnbit.store.service.base.mapper.SysUploadFileMapper;
import com.zjnbit.store.service.base.mapper.UserBaseMapper;
import com.zjnbit.store.service.base.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/17 16:27
 * @Description
 **/
@RestController
public class TestController {
    @Autowired
    UserBaseMapper userBaseMapper;
    @Autowired
    SysUploadFileMapper sysUploadFileMapper;
    @Autowired
    FileService fileService;

    @GetMapping("/test")
    public void test() {
        StpUtil.login("111",
                SaLoginConfig.setExtra("userId", 1637630173098561538L)
                        .setExtra("username", "chenjy")
                        .setExtra("email", "chenjy@chenjy.cn")
                        .setExtra("phone", "13666711991")
                        .setExtra("nickname", "非羽Army")
                        .setExtra("headpic", "https://oss.zjnbit.com/20230329/1640900247167619072.png")
        );
        System.out.println(StpUtil.getTokenInfo());
    }

}
