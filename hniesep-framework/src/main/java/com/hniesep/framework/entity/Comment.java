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
 * (Comment)表实体类
 *
 * @author makejava
 * @since 2023-04-25 15:51:48
 */
@TableName("t_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /**
     * 评论ID notnull 主键自增
     */
    @TableId
    private Long commentId;
    /**
     * 父级评论ID 默认0
     */
    private Long commentParentId;
    /**
     * 所属文章ID notnull
     */
    private Long articleId;
    /**
     * 评论用户ID notnull
     */
    @TableField(fill = FieldFill.INSERT)
    private Long accountId;
    /**
     * 发送对象ID
     */
    private Long commentToAccountId;
    /**
     * 评论内容 notnull
     */
    private String commentContent;
    /**
     * 评论点赞数 默认0
     */
    private Integer commentLikes;
    /**
     * 评论时间 自动获取
     */
    @TableField(fill = FieldFill.INSERT)
    private Date commentTime;
    /**
     * 评论状态 0:已删除 1:正常 默认1
     */
    private Integer commentStatus;
    /**
     * 点赞状态 0:未点赞 1:已点赞 默认0
     */
    private Integer commentAccountLike;

}
