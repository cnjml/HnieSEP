package com.hniesep.user.controller;

import com.hniesep.framework.entity.vo.ArticleDetailVO;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.service.impl.ArticleServiceImpl;
import org.apache.ibatis.annotations.Param;
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
    @RequestMapping ("/articleList")
    @ResponseBody
    public ResponseResult<ArticleListVO<List<ArticleVO>>> articleList(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize,@Param("boardId")Integer boardId){
        if (pageNum!=null&&pageSize!=null&&boardId!=null){
            return articleService.articleList(pageNum,pageSize,boardId);
        }
        else {
            if (pageNum!=null && pageSize!=null){
                return articleService.articleList(pageNum,pageSize);
            }
        }
        return articleService.articleList();
    }
    @GetMapping("/articleDetail/{articleId}")
    @ResponseBody
    public ResponseResult<ArticleDetailVO> articleDetail(@PathVariable("articleId")Integer articleId){
        return articleService.articleDetail(articleId);
    }
}
