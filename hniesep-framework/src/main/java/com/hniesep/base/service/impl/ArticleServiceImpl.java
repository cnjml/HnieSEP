package com.hniesep.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.base.entity.Article;
import com.hniesep.base.entity.vo.ArticleVO;
import com.hniesep.base.entity.vo.ResponseVO;
import com.hniesep.base.mapper.ArticleMapper;
import com.hniesep.base.service.ArticleService;
import com.hniesep.base.util.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> getAll() {
        return articleMapper.selectAll();
    }

    @Override
    public ResponseVO<List<ArticleVO>> popularArticles() {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getArticleAudit, 1);
        articleLambdaQueryWrapper.eq(Article::getArticleRelease, 1);
        articleLambdaQueryWrapper.orderByAsc(Article::getArticleReads);
        Page<Article> page = new Page<>(1, 10);
        page(page, articleLambdaQueryWrapper);
        List<Article> articles = page.getRecords();
        List<ArticleVO> popularArticles = BeanUtil.copyBeanList(articles,ArticleVO.class);
        return new ResponseVO<>(200, "OK", popularArticles);
    }
}