package com.hniesep.framework.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * (Comment)表实体类
 *
 * @author makejava
 * @since 2023-04-24 10:57:37
 */
@TableName("t_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /**
     * 评论ID
     */
    @TableId
     private Integer commentId;
    /**
     * 父级评论ID
     */
     private Integer commentParentId;
    /**
     * 所属文章ID
     */
     private Integer articleId;
    /**
     * 评论用户ID
     */
     private Integer accountId;
    /**
     * 评论用户IP地址
     */
     private String accountIpAddress;
    /**
     * 发送给谁的评论
     */
     private Integer commentToAccountId;
    /**
     * 评论内容
     */
     private String commentContent;
    /**
     * 评论图片路径
     */
     private String commentImagePath;
    /**
     * 评论点赞数
     */
     private Integer commentLikes;
    /**
     * 评论时间
     */
     private Date commentTime;
    /**
     * 审核状态 -1:已删除 0:未审核 1:通过审核
     */
     private Integer commentStatus;

}

