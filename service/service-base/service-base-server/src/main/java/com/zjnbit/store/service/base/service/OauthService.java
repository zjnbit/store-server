package com.zjnbit.store.service.base.service;


import com.zjnbit.store.framework.model.base.param.OauthSigninParam;
import com.zjnbit.store.framework.model.base.param.OauthSignupCodeParam;
import com.zjnbit.store.framework.model.base.param.OauthSignupParam;
import com.zjnbit.store.framework.model.base.vo.OauthSigninVo;
import me.zhyd.oauth.model.AuthCallback;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/28 11:05
 * @Description
 **/
public interface OauthService {

    Boolean signupCode(OauthSignupCodeParam data);

    OauthSigninVo signup(OauthSignupParam data);

    OauthSigninVo signin(OauthSigninParam data);

    String getRenderUrlBySource(String source);

    OauthSigninVo signinByThird(String source, AuthCallback callback);

}
