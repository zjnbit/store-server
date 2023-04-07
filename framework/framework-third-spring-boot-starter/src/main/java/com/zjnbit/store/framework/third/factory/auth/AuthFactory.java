package com.zjnbit.store.framework.third.factory.auth;

import com.zjnbit.store.framework.third.model.ThirdUser;
import com.zjnbit.store.framework.web.model.RequestError;
import com.zjnbit.store.framework.web.model.RequestException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/28 13:57
 * @Description
 **/
public class AuthFactory {

    public ThirdUser getThirdUser(String source, String config, AuthCallback authCallback) {
        AuthRequest authRequest = AuthRequestBuilder.buildAuthRequest(source, config);
        AuthResponse authResponse = authRequest.login(authCallback);
        if (authResponse.ok()) {
            switch (source) {
                case "github":
                case "gitee":
                case "baidu":
                case "qq":
                    ThirdUser thirdUser = new ThirdUser();
                    thirdUser.setOpenId(((AuthUser) authResponse.getData()).getToken().getOpenId());
                    thirdUser.setUnionId(((AuthUser) authResponse.getData()).getToken().getUnionId());
                    thirdUser.setNickname(((AuthUser) authResponse.getData()).getNickname());
                    thirdUser.setHeadpic(((AuthUser) authResponse.getData()).getAvatar());
                    return thirdUser;
                default:
                    throw new RequestException(RequestError.C0002);
            }
        } else {
            throw new RequestException(RequestError.C0001);
        }
    }

    public String renderUrl(String source, String config) {
        AuthRequest authRequest = AuthRequestBuilder.buildAuthRequest(source, config);
        return authRequest.authorize(AuthStateUtils.createState());
    }

}
