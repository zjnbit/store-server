package com.zjnbit.store.framework.model.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.store.framework.web.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/30 10:04
 * @Description ${description}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_notice_provider")
public class SysNoticeProviderEntity extends BaseEntity {
    @TableField(value = "provider_name")
    private String providerName;

    /**
     * 类型(sms,email)
     */
    @TableField(value = "provider_type")
    private String providerType;

    @TableField(value = "`source`")
    private String source;

    @TableField(value = "is_default")
    private Boolean isDefault;

    @TableField(value = "provider_config")
    private String providerConfig;
}