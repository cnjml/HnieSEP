package com.hniesep.framework.entity.bo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HKRR
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBO {
    /**
     * 所属板块ID 默认0
     */
    @TableField(fill = FieldFill.DEFAULT)
    private Long boardId;
    /**
     * 标题 notnull
     */
    private String title;
    /**
     * 话题
     */
    private String topic;
    /**
     * 文章内容 notnull
     */
    private String content;
    /**
     * 发布状态 -1: 私密 0:草稿 1:发布 默认0
     */
    private Integer release;
    /**
     * 文章id
     */
    private Long articleId;
}
