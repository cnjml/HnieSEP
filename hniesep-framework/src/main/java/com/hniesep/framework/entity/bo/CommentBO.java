package com.hniesep.framework.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 评论ID
     */
    private Long commentId;
    /**
     * 父级评论ID
     */
    private Long commentParentId;
    /**
     * 所属文章ID
     */
    private Long articleId;
    /**
     * 发送给谁的评论
     */
    private Long commentToAccountId;
    /**
     * 评论内容
     */
    private String commentContent;
}
