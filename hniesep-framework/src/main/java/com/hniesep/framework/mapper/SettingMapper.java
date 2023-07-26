package com.hniesep.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Setting;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Setting)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-17 18:57:38
 */
@Mapper
public interface SettingMapper extends BaseMapper<Setting> {

}

