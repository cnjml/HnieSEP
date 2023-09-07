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
public class FavoriteArticleListVO<T> {
    /**
     * 数量
     */
    private Integer total;
    /**
     * 收藏列表
     */
    private T rows;
}
