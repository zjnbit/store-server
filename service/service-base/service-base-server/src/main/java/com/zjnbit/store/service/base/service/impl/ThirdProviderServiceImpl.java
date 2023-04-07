package com.zjnbit.store.service.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.store.framework.cache.constant.CacheNameConst;
import com.zjnbit.store.framework.common.constant.StringPool;
import com.zjnbit.store.framework.model.base.entity.SysThirdProviderEntity;
import com.zjnbit.store.framework.third.constant.ProviderTypeEnum;
import com.zjnbit.store.service.base.mapper.SysThirdProviderMapper;
import com.zjnbit.store.service.base.service.ThirdProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/28 11:08
 * @Description
 **/
@Service
public class ThirdProviderServiceImpl implements ThirdProviderService {

    @Autowired
    SysThirdProviderMapper sysThirdProviderMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public String getConfigByTypeAndSource(String type, String source) {
        if (redisTemplate.hasKey(CacheNameConst.CONF_THIRD + type + StringPool.COLON + source)) {
            return (String) redisTemplate.opsForValue().get(CacheNameConst.CONF_THIRD + type + StringPool.COLON + source);
        }
        SysThirdProviderEntity sysThirdProviderEntity = sysThirdProviderMapper.selectOne(
                new LambdaQueryWrapper<SysThirdProviderEntity>()
                        .eq(SysThirdProviderEntity::getProviderType, type)
                        .eq(SysThirdProviderEntity::getSource, source)
        );
        redisTemplate.opsForValue().set(CacheNameConst.CONF_THIRD + type + StringPool.COLON + source, sysThirdProviderEntity.getProviderConfig());
        return sysThirdProviderEntity.getProviderConfig();
    }

    @Override
    public String getAuthConfigBySource(String source) {
        return getConfigByTypeAndSource(ProviderTypeEnum.OAUTH.getType(), source);
    }
}
