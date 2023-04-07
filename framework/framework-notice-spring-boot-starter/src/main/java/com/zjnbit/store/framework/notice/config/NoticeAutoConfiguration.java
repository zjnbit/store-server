package com.zjnbit.store.framework.notice.config;

import com.zjnbit.store.framework.notice.template.EmailTemplate;
import com.zjnbit.store.framework.notice.template.NoticeTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/30 10:29
 * @Description
 **/
@Configuration
public class NoticeAutoConfiguration {

    @Bean
    @ConditionalOnBean(EmailTemplate.class)
    public NoticeTemplate noticeTemplate(EmailTemplate emailTemplate) {
        return new NoticeTemplate(emailTemplate);
    }
}
