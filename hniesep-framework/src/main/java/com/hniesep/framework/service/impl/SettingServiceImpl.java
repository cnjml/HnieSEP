package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.mapper.SettingMapper;
import com.hniesep.framework.entity.Setting;
import com.hniesep.framework.protocol.FieldMessage;
import com.hniesep.framework.service.SettingService;
import org.springframework.stereotype.Service;

/**
 * (Setting)表服务实现类
 *
 * @author makejava
 * @since 2023-05-17 19:00:17
 */
@Service("settingService")
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {
    private final SettingMapper settingMapper;
    public SettingServiceImpl(SettingMapper settingMapper){

        this.settingMapper = settingMapper;
    }

    @Override
    public boolean isArticleNeedAudit() {
        LambdaQueryWrapper<Setting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Setting::getSettingName, FieldMessage.ARTICLE_AUDIT_KEY);
        return settingMapper.selectOne(lambdaQueryWrapper).getSettingValue().equals(FieldMessage.ARTICLE_AUDIT_ENABLE);
    }
}

