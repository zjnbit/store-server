package com.zjnbit.store.framework.model.base.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import com.zjnbit.store.framework.model.base.entity.UserBaseEntity;
import lombok.Data;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/29 10:29
 * @Description
 **/
@Data
public class OauthSigninVo {
    private Long userId;
    private String nickname;
    private String headpic;
    private String token;

    public static OauthSigninVo build(UserBaseEntity userBaseEntity){
        OauthSigninVo result = BeanUtil.copyProperties(userBaseEntity, OauthSigninVo.class);
        result.setUserId(userBaseEntity.getId());
        return result;
    }
}
