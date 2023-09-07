package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hniesep.framework.entity.Article;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.mapper.ArticleMapper;
import com.hniesep.framework.service.SearchService;
import com.hniesep.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HKRR
 */
@Service
public class SearchServiceImpl implements SearchService {
    private ArticleMapper articleMapper;
    @Override
    public ResponseResult<ArticleListVO<List<ArticleVO>>> searchArticle(String keyWord) {
        ArticleListVO<List<ArticleVO>> result = new ArticleListVO<>();
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Article::getArticleContent,keyWord).or().like(Article::getArticleTitle,keyWord);
        List<ArticleVO> articles = BeanUtil.copyBeanList(articleMapper.selectList(lambdaQueryWrapper),ArticleVO.class);
        result.setRows(articles);
        result.setTotal((long) articles.size());
        return ResponseResult.success(result);
    }
    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper){
        this.articleMapper = articleMapper;
    }
}
