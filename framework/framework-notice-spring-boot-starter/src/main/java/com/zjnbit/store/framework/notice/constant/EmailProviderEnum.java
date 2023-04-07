package com.zjnbit.store.framework.notice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/30 11:05
 * @Description
 **/
@Getter
@AllArgsConstructor
public enum EmailProviderEnum {
    NO_REPLY("no-reply","无需回复"),
    ;

    private String name;
    private String desc;

}
