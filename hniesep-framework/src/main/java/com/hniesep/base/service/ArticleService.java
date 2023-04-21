package com.hniesep.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.base.entity.Article;
import com.hniesep.base.entity.vo.ResponseVO;

import java.util.List;

/**
 * @author 吉铭炼
 */
public interface ArticleService extends IService<Article> {
    /**
     * 获取所有文章
     *
     * @return 文章列表
     */
    List<Article> getAll();

    /**
     * 返回热门文章
     * @return 视图层对象
     */
    ResponseVO popularArticles();
}
