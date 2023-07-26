package com.hniesep.user.controller;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.service.impl.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HKRR
 */
@RestController
@RequestMapping("/like")
public class LikeController {
    private LikeServiceImpl likeService;
    @Autowired
    public void setLikeService(LikeServiceImpl likeService){
        this.likeService = likeService;
    }
    @PostMapping("/article/{articleId}/toAccountId/{toAccountId}")
    @ResponseBody
    public ResponseResult<Object> likeArticle(@PathVariable("articleId") Long articleId,@PathVariable("toAccountId") Long toAccountId){
        return likeService.likeArticle(articleId,toAccountId);
    }
    @PostMapping("/comment/{commentId}/toAccountId/{toAccountId}")
    @ResponseBody
    public ResponseResult<Object> likeComment(@PathVariable("commentId") Long commentId,@PathVariable("toAccountId") Long toAccountId){
        return likeService.likeComment(commentId,toAccountId);
    }
    @GetMapping("/checkLikedArticle/{articleId}")
    @ResponseBody
    public ResponseResult<Object> checkLikedArticle(@PathVariable("articleId") Long articleId){
        return likeService.checkLikedArticle(articleId);
    }
    @GetMapping("/checkLikedComment/{commentId}")
    @ResponseBody
    public ResponseResult<Object> checkLikedComment(@PathVariable("commentId") Long commentId){
        return likeService.checkLikedComment(commentId);
    }
    @DeleteMapping("/cancelLikeArticle/{articleId}")
    @ResponseBody
    public ResponseResult<Object> cancelLikeArticle(@PathVariable("articleId") Long articleId){
        return likeService.cancelLikeArticle(articleId);
    }
    @DeleteMapping("/cancelLikeComment/{commentId}")
    @ResponseBody
    public ResponseResult<Object> cancelLikeComment(@PathVariable("commentId") Long commentId){
        return likeService.cancelLikeComment(commentId);
    }
}
