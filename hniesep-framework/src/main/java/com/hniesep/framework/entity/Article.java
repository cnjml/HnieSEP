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
 * (Article)表实体类
 *
 * @author makejava
 * @since 2023-04-25 15:49:36
 */
@TableName("t_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    /**
     * 文章ID notnull 主键自增
     */
    @TableId
    private Long articleId;
    /**
     * 所属板块ID 默认0
     */
    private Long boardId;
    /**
     * 作者ID notnull
     */
    @TableField(fill = FieldFill.INSERT)
    private Long accountId;
    /**
     * 标题 notnull
     */
    private String articleTitle;
    /**
     * 封面缩略图url
     */
    private String articleCover;
    /**
     * 话题
     */
    private String articleTopic;
    /**
     * 文章内容 notnull
     */
    private String articleContent;
    /**
     * 阅读数 默认0
     */
    private Integer articleReads;
    /**
     * 点赞数 默认0
     */
    private Integer articleLikes;
    /**
     * 置顶评论的ID
     */
    private Long articleTopCommendId;
    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date articleCreateTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date articleUpdateTime;
    /**
     * 审核状态 -1:未通过审核 0:未审核 1:通过审核 默认0
     */
    private Integer articleAudit;
    /**
     * 发布状态 -1: 私密 0:草稿 1:发布 默认0
     */
    private Integer articleRelease;
    /**
     * 逻辑删除  0: 正常 1:删除 默认0
     */
    private Integer articleDelFlag;

    public Article(Long articleId, Integer reads) {
        this.setArticleId(articleId);
        this.setArticleReads(reads);
    }
}
