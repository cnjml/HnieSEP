package com.hniesep.base.search.service;

import com.hniesep.base.entity.Article;

import java.util.List;

/**
 * @author 吉铭炼
 */
public interface ArticleService {
    /**
     * 获取所有文章
     * @return 文章列表
     */
    List<Article> getAll();
}
