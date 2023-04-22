package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Article;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.entity.vo.ResponseResult;

import java.util.List;

/**
 * @author 吉铭炼
 */
public interface ArticleService extends IService<Article> {
    /**
     * 返回热门文章
     * @return 视图层对象
     */
    ResponseResult<List<ArticleVO>> popularArticles();
    /**
     * 获取文章列表
     *
     * @param pageIndex 页码
     * @param pageSize  文章数量
     * @param boardId   板块id
     * @return 文章列表
     */
    ResponseResult<ArticleListVO<List<ArticleVO>>> articleList(Integer pageIndex, Integer pageSize, Integer boardId);

    /**
     * 获取指定板块的文章列表
     *
     * @param boardId 板块id
     * @return 文章列表
     */
    ResponseResult<ArticleListVO<List<ArticleVO>>> articleList(Integer boardId);

    /**
     * 获取文章列表
     *
     * @return 文章列表
     */
    ResponseResult<ArticleListVO<List<ArticleVO>>> articleList();
    /**
     * 获取文章列表
     * @param pageIndex 页码
     * @param pageSize  文章数量
     * @return 文章列表
     */
    ResponseResult<ArticleListVO<List<ArticleVO>>> articleList(Integer pageIndex, Integer pageSize);
}
