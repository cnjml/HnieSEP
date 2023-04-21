package com.hniesep.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2023-04-20 11:11:31
 */
@TableName("t_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    /**
     * 文章ID
     */
    @TableId
    private Integer articleId;
    /**
     * 所属板块ID
     */
    private Integer boardId;
    /**
     * 所属板块名称
     */
    private String boardName;
    /**
     * 作者ID
     */
    private Integer accountId;
    /**
     * 标题
     */
    private String articleTitle;
    /**
     * 话题
     */
    private String articleTopic;
    /**
     * 内容
     */
    private String articleContent;
    /**
     * 阅读数
     */
    private Integer articleReads;
    /**
     * 点赞数
     */
    private Integer articleLikes;
    /**
     * 评论数
     */
    private Integer articleComments;
    /**
     * 置顶评论
     */
    private Integer articleTopComment;
    /**
     * 发布时间
     */
    private Date articleCreateTime;
    /**
     * 修改时间
     */
    private Date articleUpdateTime;
    /**
     * 审核状态 0:未审核 1:通过审核
     */
    private Integer articleAudit;
    /**
     * 发布状态 -1: 已删除 0:草稿 1:发布
     */
    private Integer articleRelease;

}

