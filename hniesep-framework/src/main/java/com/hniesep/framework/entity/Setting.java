package com.hniesep.framework.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * (Setting)表实体类
 *
 * @author makejava
 * @since 2023-05-17 18:57:32
 */
@TableName("t_setting")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting implements Serializable {
    /**
     * 系统设置ID
     */
    @TableId
    private Long settingId;
    /**
     * 设置名
     */
    private String settingName;
    /**
     * 设置值
     */
    private String settingValue;

}

