package com.zjnbit.store.service.base.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.store.framework.model.base.entity.SysNoticeProviderEntity;
import com.zjnbit.store.framework.notice.config.EmailConf;
import com.zjnbit.store.framework.notice.template.EmailTemplate;
import com.zjnbit.store.service.base.mapper.SysNoticeProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/30 10:00
 * @Description
 **/
@Configuration
public class NoticeConfiguration {
    @Autowired
    SysNoticeProviderMapper sysNoticeProviderMapper;

    @Bean
    @ConditionalOnMissingBean(EmailTemplate.class)
    public EmailTemplate emailTemplate() {
        List<SysNoticeProviderEntity> sysNoticeProviderEntityList = sysNoticeProviderMapper.selectList(
                new LambdaQueryWrapper<SysNoticeProviderEntity>()
                        .eq(SysNoticeProviderEntity::getProviderType, "email")
        );
        Map<String, EmailConf> config = MapUtil.newHashMap();
        if (CollectionUtil.isNotEmpty(sysNoticeProviderEntityList)) {
            for (SysNoticeProviderEntity sysNoticeProviderEntity : sysNoticeProviderEntityList) {
                EmailConf emailConf = new EmailConf();
                emailConf.setId(sysNoticeProviderEntity.getId());
                emailConf.setIsDefault(sysNoticeProviderEntity.getIsDefault());
                emailConf.setValue(JSON.parseObject(sysNoticeProviderEntity.getProviderConfig(), EmailConf.Value.class));
                config.put(sysNoticeProviderEntity.getProviderName(), emailConf);
            }
        }
        return new EmailTemplate(config) {
            @Override
            public void init() {
                this.config.clear();
                List<SysNoticeProviderEntity> sysNoticeProviderEntityList = sysNoticeProviderMapper.selectList(
                        new LambdaQueryWrapper<SysNoticeProviderEntity>()
                                .eq(SysNoticeProviderEntity::getProviderType, "email")
                );
                if (CollectionUtil.isNotEmpty(sysNoticeProviderEntityList)) {
                    for (SysNoticeProviderEntity sysNoticeProviderEntity : sysNoticeProviderEntityList) {
                        EmailConf emailConf = new EmailConf();
                        emailConf.setId(sysNoticeProviderEntity.getId());
                        emailConf.setIsDefault(sysNoticeProviderEntity.getIsDefault());
                        emailConf.setValue(JSON.parseObject(sysNoticeProviderEntity.getProviderConfig(), EmailConf.Value.class));
                        this.config.put(sysNoticeProviderEntity.getProviderName(), emailConf);
                    }
                }

            }
        };
    }
}
