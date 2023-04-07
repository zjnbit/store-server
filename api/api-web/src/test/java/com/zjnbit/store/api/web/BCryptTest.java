package com.zjnbit.store.api.web;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.MD5;
import org.junit.Test;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/27 16:51
 * @Description
 **/
public class BCryptTest {

    @Test
    public void xx() {
        String plaintest = "123456";
        String hex = MD5.create().digestHex(plaintest);
        String hashed= BCrypt.hashpw(hex, BCrypt.gensalt());
        System.out.println(hex);
        System.out.println(hashed);
        System.out.println(BCrypt.checkpw(hex, hashed));
    }
}
