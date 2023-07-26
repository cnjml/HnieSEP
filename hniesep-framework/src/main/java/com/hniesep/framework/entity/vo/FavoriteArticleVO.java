package com.hniesep.framework.entity.vo;

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
    private Long favoriteId;
    /**
     * 收藏文章id
     */
    private Long favoriteObjectId;
    /**
     * 文章标题
     */
    private String articleTitle;
}
