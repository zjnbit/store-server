package com.zjnbit.store.service.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnbit.store.framework.model.base.entity.UserBaseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/29 15:34
 * @Description ${description}
 **/
@Mapper
public interface UserBaseMapper extends BaseMapper<UserBaseEntity> {
}