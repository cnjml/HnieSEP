package com.hniesep.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.hniesep.base.entity.Article;
import com.hniesep.base.search.service.ArticleService;

/**
 * @author 吉铭炼
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Article> getAll(){
        return articleService.getAll();
    }
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}