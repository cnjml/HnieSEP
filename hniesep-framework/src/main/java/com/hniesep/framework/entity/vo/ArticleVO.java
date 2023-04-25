package com.hniesep.framework.entity.vo;

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
 * @since 2023-04-22 12:04:23
 */
@TableName("t_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO implements Serializable {
    /**
     * 文章ID notnull 主键自增
     */
    private Long articleId;
    /**
     * 所属板块ID 默认0
     */
    private Long boardId;
    /**
     * 作者ID notnull
     */
    private Long accountId;
    /**
     * 作者昵称
     */
    private String accountNickName;
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
    private Date articleCreateTime;
    /**
     * 修改时间
     */
    private Date articleUpdateTime;

}
