package com.hniesep.user.controller;

import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.entity.vo.ResponseResult;
import com.hniesep.framework.service.impl.ArticleServiceImpl;
import org.apache.ibatis.annotations.Param;
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
}
