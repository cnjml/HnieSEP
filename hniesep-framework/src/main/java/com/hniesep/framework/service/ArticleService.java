package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Article;
import com.hniesep.framework.entity.vo.ArticleDetailVO;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.entity.ResponseResult;

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
     * 根据id获取文章详情
     * @param articleId 文章id
     * @return 文章详情
     */
    ResponseResult<ArticleDetailVO> articleDetail(Integer articleId);
}
