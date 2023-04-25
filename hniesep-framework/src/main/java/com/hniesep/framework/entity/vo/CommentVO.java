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
     * 评论ID notnull 主键自增
     */
    private Long commentId;
    /**
     * 评论用户昵称
     */
    private String accountNickName;
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
     * 发送对象的昵称
     */
    private String commentToAccountNickName;
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
     * 子评论
     */
    private List<CommentVO> children;

}
