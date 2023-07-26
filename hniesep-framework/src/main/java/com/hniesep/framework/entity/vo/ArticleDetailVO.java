package com.hniesep.framework.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long articleId;
    /**
     * 所属板块ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long boardId;
    /**
     * 作者ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    /**
     * 作者头像
     */
    private String accountAvatar;
    /**
     * 作者昵称
     */
    private String accountNickname;
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
