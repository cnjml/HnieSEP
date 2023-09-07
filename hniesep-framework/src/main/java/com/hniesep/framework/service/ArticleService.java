package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Article;
import com.hniesep.framework.entity.bo.ArticleBO;
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
    ResponseResult<ArticleListVO<List<ArticleVO>>> articleList(Integer pageIndex, Integer pageSize, Long boardId);
    /**
     * 根据id获取文章详情
     * @param articleId 文章id
     * @return 文章详情
     */
    ResponseResult<ArticleDetailVO> articleDetail(Long articleId);

    /**
     * 增加浏览量
     * @param articleId 文章id
     * @return 响应结果
     */
    ResponseResult<Object> updateReads(Long articleId);

    /**
     * 添加文章
     * @param articleBO 文章业务对象
     * @return 响应
     */
    ResponseResult<Object> addArticle(ArticleBO articleBO);

    /**
     * 根据id获取文章标题
     * @param articleId 文章id
     * @return 文章标题
     */
    String getTitleById(Long articleId);

    /**
     * 更新文章
     * @param articleBO 文章业务对象
     * @return 操作结果
     */
    ResponseResult<Object> updateArticle(ArticleBO articleBO);

    /**
     * 删除文章
     * @param articleBO 文章业务对象
     * @return 操作结果
     */
    ResponseResult<Object> deleteArticle(ArticleBO articleBO);
}
