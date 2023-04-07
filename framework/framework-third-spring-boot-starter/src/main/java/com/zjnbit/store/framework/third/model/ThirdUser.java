package com.zjnbit.store.framework.third.model;

import lombok.Data;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/28 15:18
 * @Description
 **/
@Data
public class ThirdUser {
    private String openId;
    private String unionId;
    private String nickname;

    private String headpic;

}
