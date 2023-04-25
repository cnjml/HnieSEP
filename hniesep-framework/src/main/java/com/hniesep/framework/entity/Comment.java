package com.hniesep.framework.entity;

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
    private Integer accountId;
    /**
     * 评论用户IP地址
     */
    private String accountIpAddress;
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
    private Date commentTime;
    /**
     * 审核状态 -1:已删除 0:未审核 1:通过审核 默认0
     */
    private Integer commentStatus;

}
