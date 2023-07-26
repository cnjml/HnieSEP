package com.hniesep.framework.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Favorite)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-04 13:19:58
 */
@Mapper
@DS("user")
public interface FavoriteMapper extends BaseMapper<Favorite> {

}

