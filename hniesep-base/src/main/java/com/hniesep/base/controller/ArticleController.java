package com.hniesep.base.controller;

import com.hniesep.base.entity.Article;
import com.hniesep.base.entity.vo.ResponseVO;
import com.hniesep.base.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    private ArticleServiceImpl articleService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Article> getAll() {
        return articleService.getAll();
    }

    @Autowired
    public void setArticleService(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/popularArticles")
    @ResponseBody
    public ResponseVO popularArticles() {
        ResponseVO responseVO = articleService.popularArticles();
        return responseVO;
    }
}
