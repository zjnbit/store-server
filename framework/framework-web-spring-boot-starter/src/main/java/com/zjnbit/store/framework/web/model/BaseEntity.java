package com.zjnbit.store.framework.web.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zjnbit.store.framework.common.constant.DatePatternConst;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/17 14:20
 * @Description
 **/
@Data
public class BaseEntity implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    @JsonFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    @JsonFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    private LocalDateTime updateTime;

    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    @TableField(value = "version")
    private Integer version;
}
