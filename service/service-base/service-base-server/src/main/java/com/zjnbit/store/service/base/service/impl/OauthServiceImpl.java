package com.zjnbit.store.service.base.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.store.framework.cache.constant.CacheNameConst;
import com.zjnbit.store.framework.model.base.entity.UserBaseEntity;
import com.zjnbit.store.framework.model.base.entity.UserThirdLoginEntity;
import com.zjnbit.store.framework.model.base.param.OauthSigninParam;
import com.zjnbit.store.framework.model.base.param.OauthSignupCodeParam;
import com.zjnbit.store.framework.model.base.param.OauthSignupParam;
import com.zjnbit.store.framework.model.base.vo.OauthSigninVo;
import com.zjnbit.store.framework.notice.constant.EmailProviderEnum;
import com.zjnbit.store.framework.notice.template.NoticeTemplate;
import com.zjnbit.store.framework.third.factory.ThirdFactory;
import com.zjnbit.store.framework.third.model.ThirdUser;
import com.zjnbit.store.framework.web.model.RequestError;
import com.zjnbit.store.framework.web.model.RequestException;
import com.zjnbit.store.service.base.mapper.UserBaseMapper;
import com.zjnbit.store.service.base.mapper.UserThirdLoginMapper;
import com.zjnbit.store.service.base.service.OauthService;
import com.zjnbit.store.service.base.service.ThirdProviderService;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/28 11:06
 * @Description
 **/
@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    UserBaseMapper userBaseMapper;
    @Autowired
    UserThirdLoginMapper userThirdLoginMapper;
    @Autowired
    ThirdProviderService thirdProviderService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    NoticeTemplate noticeTemplate;

    @Override
    public Boolean signupCode(OauthSignupCodeParam data) {
        if ("email".equals(data.getType()) && Validator.isEmail(data.getTarget())) {
            if (redisTemplate.hasKey(CacheNameConst.OAUTH_SIGNUP_EMAIL_TIMER + data.getTarget())) {
                throw new RequestException(RequestError.A1104);
            }
            long emailCount = userBaseMapper.selectCount(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getEmail, data.getTarget()));
            if (emailCount > 0) {
                throw new RequestException(RequestError.A1102);
            }
            String code = RandomUtil.randomNumbers(6);
            redisTemplate.opsForValue().set(CacheNameConst.OAUTH_SIGNUP_EMAIL_CODE + data.getTarget(), code, 30, TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(CacheNameConst.OAUTH_SIGNUP_EMAIL_TIMER + data.getTarget(), null, 1, TimeUnit.MINUTES);
            noticeTemplate.EMAIL.sendSimpleMail(EmailProviderEnum.NO_REPLY.getName(), data.getTarget(), "注册验证码", "您的验证码为：" + code);
        } else if ("phone".equals(data.getType())) {

        } else {
            throw new RequestException(RequestError.A0003);
        }
        return true;
    }

    @Override
    public OauthSigninVo signup(OauthSignupParam data) {
        //验证用户名是否存在
        long usernameCount = userBaseMapper.selectCount(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getUsername, data.getUsername()));
        if (usernameCount > 0) {
            throw new RequestException(RequestError.A1101);
        }
        long emailCount = userBaseMapper.selectCount(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getEmail, data.getEmail()));
        if (emailCount > 0) {
            throw new RequestException(RequestError.A1102);
        }
        //验证验证码
        if (redisTemplate.hasKey(CacheNameConst.OAUTH_SIGNUP_EMAIL_CODE + data.getEmail())) {
            String code = (String) redisTemplate.opsForValue().get(CacheNameConst.OAUTH_SIGNUP_EMAIL_CODE + data.getEmail());
            if (!code.equals(data.getCode())) {
                throw new RequestException(RequestError.A1103);
            }
        } else {
            throw new RequestException(RequestError.A1103);
        }
        //校验完毕，创建账号
        UserBaseEntity userBaseEntity = new UserBaseEntity();
        userBaseEntity.setUsername(data.getUsername());
        userBaseEntity.setEmail(data.getEmail());
        userBaseEntity.setPassword(BCrypt.hashpw(data.getPassword().toLowerCase(), BCrypt.gensalt()));
        userBaseEntity.setNickname(data.getNickname());
        userBaseEntity.setHeadpic("https://oss.zjnbit.com/static/default_avatar.jpeg");
        userBaseEntity.setLatestLoginTime(LocalDateTime.now());
        userBaseMapper.insert(userBaseEntity);
        OauthSigninVo result = OauthSigninVo.build(userBaseEntity);
        StpUtil.login(userBaseEntity.getId()
                , SaLoginConfig
                        .setExtra("nickname", result.getNickname())
                        .setExtra("headpic", result.getHeadpic()));
        result.setToken(StpUtil.getTokenValue());
        redisTemplate.delete(CacheNameConst.OAUTH_SIGNUP_EMAIL_CODE + data.getEmail());
        return result;
    }

    @Override
    public OauthSigninVo signin(OauthSigninParam data) {
        UserBaseEntity userBaseEntity = null;
        if ("username".equals(data.getSigninType())) {
            userBaseEntity = userBaseMapper.selectOne(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getUsername, data.getSigninId()));
        } else if ("email".equals(data.getSigninType())) {
            userBaseEntity = userBaseMapper.selectOne(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getEmail, data.getSigninId()));
        }
        if (null == userBaseEntity) {
            throw new RequestException(RequestError.A0200);
        }
        OauthSigninVo result = OauthSigninVo.build(userBaseEntity);
        StpUtil.login(userBaseEntity.getId()
                , SaLoginConfig
                        .setExtra("nickname", result.getNickname())
                        .setExtra("headpic", result.getHeadpic()));
        result.setToken(StpUtil.getTokenValue());
        return result;
    }

    @Override
    public String getRenderUrlBySource(String source) {
        String config = thirdProviderService.getAuthConfigBySource(source);
        return ThirdFactory.auth().renderUrl(source, config);
    }

    @Override
    public OauthSigninVo signinByThird(String source, AuthCallback callback) {
        String config = thirdProviderService.getAuthConfigBySource(source);
        ThirdUser thirdUser = ThirdFactory.auth().getThirdUser(source, config, callback);
        UserThirdLoginEntity userThirdLoginEntity = userThirdLoginMapper.selectOne(
                new LambdaQueryWrapper<UserThirdLoginEntity>()
                        .eq(UserThirdLoginEntity::getSource, source)
                        .eq(UserThirdLoginEntity::getOpenId, thirdUser.getOpenId())
        );
        if (null != userThirdLoginEntity) {
            UserBaseEntity userBaseEntity = userBaseMapper.selectById(userThirdLoginEntity.getUserId());
            OauthSigninVo result = OauthSigninVo.build(userBaseEntity);
            StpUtil.login(userBaseEntity.getId()
                    , SaLoginConfig
                            .setExtra("nickname", result.getNickname())
                            .setExtra("headpic", result.getHeadpic()));
            result.setToken(StpUtil.getTokenValue());
            return result;
        }
        return null;
    }

}
