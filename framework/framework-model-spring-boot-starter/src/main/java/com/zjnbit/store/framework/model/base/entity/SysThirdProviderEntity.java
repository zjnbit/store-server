package com.zjnbit.store.framework.model.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.store.framework.web.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/28 11:07
 * @Description ${description}
 **/

/**
 * 社会化源
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_third_provider")
public class SysThirdProviderEntity extends BaseEntity {
    @TableField(value = "provider_name")
    private String providerName;

    /**
     * 类型(SMS,AUTH,NOTIFY,TRADE)
     */
    @TableField(value = "provider_type")
    private String providerType;

    /**
     * 平台
     */
    @TableField(value = "`source`")
    private String source;

    /**
     * 配置
     */
    @TableField(value = "provider_config")
    private String providerConfig;
}