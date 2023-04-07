package com.zjnbit.store.framework.web.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zjnbit.store.framework.common.constant.DatePatternConst;
import com.zjnbit.store.framework.web.handle.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/17 15:45
 * @Description
 **/
@Configuration
public class WebAutoConfiguration {

    @Value("${spring.jackson.date-format:" + DatePatternConst.PATTERN_DATETIME + "}")
    private String dateTimePattern;

    @Bean
    @ConditionalOnMissingBean({GlobalExceptionHandler.class})
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
    }

}
