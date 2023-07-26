package com.hniesep.framework.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Like;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Like)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-31 19:12:08
 */
@Mapper
@DS("user")
public interface LikeMapper extends BaseMapper<Like> {

}

