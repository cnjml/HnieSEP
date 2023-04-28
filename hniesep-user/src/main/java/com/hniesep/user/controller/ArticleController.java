package com.hniesep.user.controller;

import com.hniesep.framework.entity.bo.ArticleBO;
import com.hniesep.framework.entity.vo.ArticleDetailVO;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 吉铭炼
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleServiceImpl articleService;
    @Autowired
    public void setArticleService(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }
    @GetMapping("/popularArticles")
    @ResponseBody
    public ResponseResult<List<ArticleVO>> popularArticles() {
        return articleService.popularArticles();
    }
    @GetMapping ("/articleList/pageIndex/{pageIndex}/pageSize/{pageSize}/boardId/{boardId}")
    @ResponseBody
    public ResponseResult<ArticleListVO<List<ArticleVO>>> articleList(@PathVariable("pageIndex")Integer pageIndex,@PathVariable("pageSize")Integer pageSize,@PathVariable("boardId")Long boardId){
        return articleService.articleList(pageIndex,pageSize,boardId);
    }
    @GetMapping("/articleDetail/{articleId}")
    @ResponseBody
    public ResponseResult<ArticleDetailVO> articleDetail(@PathVariable("articleId")Long articleId){
        return articleService.articleDetail(articleId);
    }
    @PostMapping("/addArticle")
    @ResponseBody
    public ResponseResult<Object> addArticle(@RequestBody ArticleBO articleBO){
        return articleService.addArticle(articleBO);
    }
}
