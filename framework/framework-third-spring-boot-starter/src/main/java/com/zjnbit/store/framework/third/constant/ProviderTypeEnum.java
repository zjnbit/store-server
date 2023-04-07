package com.zjnbit.store.framework.third.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/28 16:00
 * @Description 类型(sms, auth, notify, trade)
 **/
@Getter
@AllArgsConstructor
public enum ProviderTypeEnum {
    SMS("sms", "短信"),

    OAUTH("oauth", "授权"),

    NOTIFY("notify", "通知"),

    TRADE("trade", "交易"),

    ;
    private String type;
    private String desc;

}
