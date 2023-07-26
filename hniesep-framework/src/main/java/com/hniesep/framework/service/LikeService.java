package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Like;
import com.hniesep.framework.entity.ResponseResult;

/**
 * (Like)表服务接口
 *
 * @author makejava
 * @since 2023-05-31 19:12:08
 */
public interface LikeService extends IService<Like> {
    /**
     * 点赞评论
     * @param commentId 评论id
     * @param toAccountId 评论对象id
     * @return 响应结果
     */
    ResponseResult<Object> likeComment(Long commentId, Long toAccountId);
    /**
     * 点赞文章
     * @param articleId 文章id
     * @param toAccountId 文章作者id
     * @return 响应结果
     */
    ResponseResult<Object> likeArticle(Long articleId, Long toAccountId);

    /**
     * 是否喜欢文章
     * @param articleId 文章id
     * @return 结果
     */
    ResponseResult<Object> checkLikedArticle(Long articleId);

    /**
     * 是否喜欢评论
     * @param commentId 评论id
     * @return 结果
     */
    ResponseResult<Object> checkLikedComment(Long commentId);

    /**
     * 取消点赞文章
     * @param articleId 文章id
     * @return 操作结果
     */
    ResponseResult<Object> cancelLikeArticle(Long articleId);

    /**
     * 取消点赞评论
     * @param commentId 评论id
     * @return 操作结果
     */
    ResponseResult<Object> cancelLikeComment(Long commentId);
}

