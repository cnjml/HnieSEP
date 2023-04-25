package com.hniesep.framework.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吉铭炼
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentBO {
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
}
