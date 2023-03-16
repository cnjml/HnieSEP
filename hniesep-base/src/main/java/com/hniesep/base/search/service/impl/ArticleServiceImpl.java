package com.hniesep.base.search.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.hniesep.base.entity.Article;
import com.hniesep.base.search.mapper.ArticleMapper;
import com.hniesep.base.search.service.ArticleService;

/**
 * @author 吉铭炼
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    final ArticleMapper articleMapper;
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
    @Override
    public List<Article> getAll(){
        return articleMapper.selectAll();
    }
}