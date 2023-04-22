package com.hniesep.framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 吉铭炼
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVO implements Serializable {
    /**
     * 文章ID
     */
    private Integer articleId;
    /**
     * 所属板块ID
     */
    private Integer boardId;
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
}
