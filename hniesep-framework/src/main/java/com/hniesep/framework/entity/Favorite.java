package com.hniesep.framework.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * (Favorite)表实体类
 *
 * @author makejava
 * @since 2023-06-04 13:20:00
 */
@TableName("t_favorite")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite implements Serializable {
    /**
     * 收藏id notnull
     */
    @TableId
    private Long favoriteId;
    /**
     * 收藏者id notnull
     */
    @TableField(fill = FieldFill.INSERT)
    private Long accountId;
    /**
     * 收藏类型 0:文章 1:待定 默认0 notnull
     */
    private Integer favoriteType;
    /**
     * 收藏对象id notnull
     */
    private Long favoriteObjectId;

}

