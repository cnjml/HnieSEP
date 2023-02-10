package com.hniesep.base.service.impl;

import com.hniesep.base.entity.Article;
import com.hniesep.base.mapper.ArticleMapper;
import com.hniesep.base.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

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
