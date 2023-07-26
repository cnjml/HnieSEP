package com.hniesep.framework.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 吉铭炼
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO implements Serializable {
    /**
     * 评论ID notnull 主键自增
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentId;
    /**
     * 评论用户昵称
     */
    private String accountNickname;
    /**
     * 父级评论ID 默认0
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentParentId;
    /**
     * 所属文章ID notnull
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long articleId;
    /**
     * 评论用户ID notnull
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    /**
     * 发送对象ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentToAccountId;
    /**
     * 发送对象的昵称
     */
    private String commentToAccountNickname;
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
     * 点赞状态 0:未点赞 1:已点赞 默认0
     */
    private Integer commentAccountLike;
    /**
     * 子评论
     */
    private List<CommentVO> children;

}
