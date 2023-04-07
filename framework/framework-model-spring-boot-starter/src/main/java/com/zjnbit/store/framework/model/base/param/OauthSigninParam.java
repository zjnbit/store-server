package com.zjnbit.store.framework.model.base.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/29 10:26
 * @Description
 **/
@Data
public class OauthSigninParam {
    @NotBlank(message = "登录类型不能为空")
    private String signinType;
    @NotBlank(message = "登录名不能为空")
    private String signinId;
    @NotBlank(message = "密码不能为空")
    private String signinPwd;

}
