package com.zjnbit.store.framework.feign.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenjy
 * @emp chenjy 
 * @mobile 18158571991
 * @date 2021/12/6 08:51
 * @Description
 **/
public class FeignInterceptor implements RequestInterceptor {
    private static final Pattern JWT_TOKEN_PATTTERN = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$");

    @Override
    public void apply(RequestTemplate requestTemplate) {
        final String authorization = HttpHeaders.AUTHORIZATION;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNotNull(attributes)) {
            String jwt = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (StrUtil.isEmpty(jwt)) {
                return;
            }
            Matcher matcher = JWT_TOKEN_PATTTERN.matcher(jwt);
            if (matcher.matches()) {
                requestTemplate.header(authorization);
                requestTemplate.header(authorization, jwt);
            }
        }

    }
}
