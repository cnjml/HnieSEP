package com.hniesep.framework.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * (Like)表实体类
 *
 * @author makejava
 * @since 2023-05-31 23:33:11
 */
@TableName("t_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like implements Serializable {
    /**
     * 点赞ID notnull
     */
    @TableId
    private Long likeId;
    /**
     * 点赞类型 0:文章 1:评论 notnull
     */
    private Integer likeType;
    /**
     * 用户ID notnull
     */
    @TableField(fill = FieldFill.INSERT)
    private Long accountId;
    /**
     * 点赞对象ID notnull
     */
    private Long likeToAccountId;
    /**
     * 文章或评论ID notnull
     */
    private Long likeObjectId;
    /**
     * 创建时间
     */
    private Date likeCreateTime;

}

