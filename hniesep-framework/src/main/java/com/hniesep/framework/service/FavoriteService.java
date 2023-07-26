package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Favorite;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.FavoriteArticleVO;

import java.util.List;

/**
 * (Favorite)表服务接口
 *
 * @author makejava
 * @since 2023-06-04 13:20:00
 */
public interface FavoriteService extends IService<Favorite> {
    /**
     * 收藏文章
     * @param articleId 文章id
     * @return 操作结果
     */
    ResponseResult<Object> favoriteArticle(Long articleId);

    /**
     * 取消收藏文章
     * @param articleId 文章id
     * @return 操作结果
     */
    ResponseResult<Object> cancelFavoriteArticle(Long articleId);

    /**
     * 收藏文章列表
     * @return 文章列表
     */
    ResponseResult<List<FavoriteArticleVO>> favoriteArticleList();
}

