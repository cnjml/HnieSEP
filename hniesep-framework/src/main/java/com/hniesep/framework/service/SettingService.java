package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Setting;

/**
 * (Setting)表服务接口
 *
 * @author makejava
 * @since 2023-05-17 18:58:14
 */
public interface SettingService extends IService<Setting> {

    /**
     * 返回是否需要审核，默认需要
     * @return 是否需要审核
     */
    boolean isArticleNeedAudit();
}
