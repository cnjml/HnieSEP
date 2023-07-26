package com.hniesep.user.controller;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.FavoriteArticleVO;
import com.hniesep.framework.service.impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jml
 */
@RequestMapping("/favorite")
@RestController
public class FavoriteController {
    private FavoriteServiceImpl favoriteService;
    @Autowired
    public void setFavoriteService(FavoriteServiceImpl favoriteService){
        this.favoriteService = favoriteService;
    }
    @PostMapping("/article/{articleId}")
    @ResponseBody
    public ResponseResult<Object> favoriteArticle(@PathVariable("articleId") Long articleId){
        return favoriteService.favoriteArticle(articleId);
    }
    @GetMapping("/checkFavoriteArticle/{articleId}")
    @ResponseBody
    public ResponseResult<Object> checkFavoriteArticle(@PathVariable("articleId") Long articleId){
        return favoriteService.checkFavoriteArticle(articleId);
    }
    @DeleteMapping("/cancelFavoriteArticle/{articleId}")
    @ResponseBody
    public ResponseResult<Object> cancelFavoriteArticle(@PathVariable("articleId") Long articleId){
        return favoriteService.cancelFavoriteArticle(articleId);
    }
    @GetMapping("/articleList")
    @ResponseBody
    public ResponseResult<List<FavoriteArticleVO>> favoriteArticleList(){
        return favoriteService.favoriteArticleList();
    }
}
