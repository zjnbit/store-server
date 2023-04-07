package com.zjnbit.store.framework.mybatis.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zjnbit.store.framework.web.util.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/17 17:30
 * @Description
 **/
@Configuration
public class MyBatisPlusMetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = UserUtils.getCurrentUserId();
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = UserUtils.getCurrentUserId();
        this.setFieldValByName("updateBy", userId, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
