package com.hniesep.user.controller;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.CommentBO;
import com.hniesep.framework.entity.vo.CommentListVO;
import com.hniesep.framework.entity.vo.CommentVO;
import com.hniesep.framework.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 吉铭炼
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentServiceImpl commentService;
    @Autowired
    public void setCommentService(CommentServiceImpl commentService){
        this.commentService = commentService;
    }
    @GetMapping("/commentList/articleId/{articleId}/pageIndex/{pageIndex}/pageSize/{pageSize}")
    @ResponseBody
    public ResponseResult<CommentListVO<List<CommentVO>>> commentList(@PathVariable("articleId") Integer articleId, @PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize")Integer pageSize){
        return commentService.commentList(articleId,pageIndex,pageSize);
    }
    @ResponseBody
    public ResponseResult<Object> comment(@RequestBody CommentBO commentBO){
        return commentService.comment(commentBO);
    }
}
