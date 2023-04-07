package com.zjnbit.store.framework.third.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/28 16:05
 * @Description
 **/
@Getter
@AllArgsConstructor
public enum ProviderSourceEnum {
    GITHUB("github", "github"),
    GITEE("gitee", "gitee"),
    BAIDU("baidu", "百度"),
    QQ("qq", "QQ"),
    ;
    private String source;
    private String desc;

    public static Boolean isExist(String source) {
        for (ProviderSourceEnum providerSourceEnum : ProviderSourceEnum.values()) {
            if (providerSourceEnum.getSource().equals(source)) {
                return true;
            }
        }
        return false;
    }
}
