package com.hniesep.framework.entity.vo;

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
     * 评论ID
     */
    private Integer commentId;
    /**
     * 父级评论ID
     */
    private Integer commentParentId;
    /**
     * 子评论
     */
    private List<CommentVO> children;
    /**
     * 所属文章ID
     */
    private Integer articleId;
    /**
     * 评论用户ID
     */
    private Integer accountId;
    /**
     * 评论用户昵称
     */
    private String accountNickName;
    /**
     * 评论用户IP地址
     */
    private String accountIpAddress;
    /**
     * 发送给谁的评论
     */
    private Integer commentToAccountId;
    /**
     * 发送对象的昵称
     */
    private String commentToAccountNickName;
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
}
