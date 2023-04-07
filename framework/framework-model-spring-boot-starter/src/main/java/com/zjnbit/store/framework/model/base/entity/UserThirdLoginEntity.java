package com.zjnbit.store.framework.model.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zjnbit.store.framework.web.model.BaseEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author chenjy
* @emp chenjy 
* @date 2023/3/29 15:39
* @Description ${description}
**/
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "user_third_login")
public class UserThirdLoginEntity extends BaseEntity {
    @TableField(value = "user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @TableField(value = "`source`")
    private String source;

    @TableField(value = "open_id")
    private String openId;

    @TableField(value = "union_Id")
    private String unionId;
}