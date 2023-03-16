package com.hniesep.base.search.service;

import java.util.List;

import com.hniesep.base.entity.Article;

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
