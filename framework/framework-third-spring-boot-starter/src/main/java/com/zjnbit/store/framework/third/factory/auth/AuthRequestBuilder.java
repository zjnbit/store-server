package com.zjnbit.store.framework.third.factory.auth;

import cn.hutool.json.JSONUtil;
import com.zjnbit.store.framework.third.constant.ProviderSourceEnum;
import com.zjnbit.store.framework.web.model.RequestError;
import com.zjnbit.store.framework.web.model.RequestException;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.*;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/28 14:13
 * @Description
 **/
public class AuthRequestBuilder {

    public static AuthRequest buildAuthRequest(String source, String config) {
        if (!ProviderSourceEnum.isExist(source)) {
            throw new RequestException(RequestError.C0002);
        }
        switch (source) {
            case "github":
                return getAuthGithubRequst(config);
            case "gitee":
                return getAuthGiteeRequest(config);
            case "baidu":
                return getAuthBaiduRequest(config);
            case "qq":
                return getAuthQqRequest(config);
            default:
                throw new RequestException(RequestError.C0002);
        }
    }

    private static AuthRequest getAuthGithubRequst(String config) {
        String clientId = JSONUtil.parseObj(config).getStr("clientId");
        String clientSecret = JSONUtil.parseObj(config).getStr("clientSecret");
        String redirectUri = JSONUtil.parseObj(config).getStr("redirectUri");
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }

    private static AuthRequest getAuthGiteeRequest(String config) {
        String clientId = JSONUtil.parseObj(config).getStr("clientId");
        String clientSecret = JSONUtil.parseObj(config).getStr("clientSecret");
        String redirectUri = JSONUtil.parseObj(config).getStr("redirectUri");
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }

    private static AuthRequest getAuthBaiduRequest(String config) {
        String clientId = JSONUtil.parseObj(config).getStr("clientId");
        String clientSecret = JSONUtil.parseObj(config).getStr("clientSecret");
        String redirectUri = JSONUtil.parseObj(config).getStr("redirectUri");
        return new AuthBaiduRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }

    private static AuthRequest getAuthQqRequest(String config) {
        String clientId = JSONUtil.parseObj(config).getStr("clientId");
        String clientSecret = JSONUtil.parseObj(config).getStr("clientSecret");
        String redirectUri = JSONUtil.parseObj(config).getStr("redirectUri");
        return new AuthQqRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }
}
