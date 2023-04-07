package com.zjnbit.store.framework.third.factory;

import com.zjnbit.store.framework.third.factory.auth.AuthFactory;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/28 15:14
 * @Description
 **/
public class ThirdFactory {
    public static AuthFactory auth() {
        return new AuthFactory();
    }
}
