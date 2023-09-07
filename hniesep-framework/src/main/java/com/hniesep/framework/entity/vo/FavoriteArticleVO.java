package com.hniesep.framework.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HKRR
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteArticleVO {
    /**
     * 收藏id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long favoriteId;
    /**
     * 收藏文章id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long favoriteObjectId;
    /**
     * 文章标题
     */
    private String articleTitle;
}
