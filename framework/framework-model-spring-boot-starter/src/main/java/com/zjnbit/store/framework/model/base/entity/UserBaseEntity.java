package com.zjnbit.store.framework.model.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.store.framework.web.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/29 15:34
 * @Description ${description}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_base")
public class UserBaseEntity extends BaseEntity {
    /**
     * 登录名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 邮箱地址
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 头像
     */
    @TableField(value = "headpic")
    private String headpic;

    /**
     * 最后登录时间
     */
    @TableField(value = "latest_login_time")
    private LocalDateTime latestLoginTime;

    @TableField(value = "`password`")
    private String password;
}