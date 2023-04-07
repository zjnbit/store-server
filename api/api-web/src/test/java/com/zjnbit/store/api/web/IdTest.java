package com.zjnbit.store.api.web;

import cn.hutool.core.util.IdUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/30 08:34
 * @Description
 **/
public class IdTest {
    @Test
    @SneakyThrows
    void test() {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            System.out.println(IdUtil.getSnowflake().nextIdStr());
        }

    }
}
