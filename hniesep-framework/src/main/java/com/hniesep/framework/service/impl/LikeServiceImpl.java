package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.LikeMapper;
import com.hniesep.framework.entity.Like;
import com.hniesep.framework.protocol.FieldCode;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.LikeService;
import com.hniesep.framework.util.SecurityUtil;
import org.springframework.stereotype.Service;

/**
 * (Like)表服务实现类
 *
 * @author makejava
 * @since 2023-05-31 22:43:43
 */
@Service("likeService")
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {
    private final LikeMapper likeMapper;
    public LikeServiceImpl(LikeMapper likeMapper){
        this.likeMapper=likeMapper;
    }
    @Override
    public ResponseResult<Object> likeArticle(Long articleId, Long toAccountId){
        if(isLikedArticle(articleId)){
            return ResponseResult.fail(HttpResultEnum.ARTICLE_ALREADY_LIKE);
        }
        Like like = new Like();
        like.setLikeObjectId(articleId);
        like.setLikeToAccountId(toAccountId);
        like.setLikeType(FieldCode.LIKE_ARTICLE);
        likeMapper.insert(like);
        return ResponseResult.success();
    }
    @Override
    public ResponseResult<Object> likeComment(Long commentId, Long toAccountId){
        if (isLikedComment(commentId)){
            return ResponseResult.fail(HttpResultEnum.COMMENT_ALREADY_LIKE);
        }
         Like like = new Like();
         like.setLikeObjectId(commentId);
         like.setLikeToAccountId(toAccountId);
         like.setLikeType(FieldCode.LIKE_COMMENT);
         likeMapper.insert(like);
         return ResponseResult.success();
    }
    private boolean isLikedArticle(Long articleId){
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Like::getLikeType,FieldCode.LIKE_ARTICLE);
        lambdaQueryWrapper.eq(Like::getLikeObjectId, articleId);
        return likeMapper.selectList(lambdaQueryWrapper).size() != 0;
    }
    private boolean isLikedComment(Long commentId){
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getLikeType,FieldCode.LIKE_COMMENT);
        lambdaQueryWrapper.eq(Like::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Like::getLikeObjectId, commentId);
        return likeMapper.selectList(lambdaQueryWrapper).size() != 0;
    }
    public boolean isCommentLiked(Long commentId,Long accountId){
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getLikeType,FieldCode.LIKE_COMMENT);
        lambdaQueryWrapper.eq(Like::getAccountId, accountId);
        lambdaQueryWrapper.eq(Like::getLikeObjectId, commentId);
        return likeMapper.selectList(lambdaQueryWrapper).size() != 0;
    }
    @Override
    public ResponseResult<Object> checkLikedArticle(Long articleId){
        if(isLikedArticle(articleId)){
            return new ResponseResult<>(HttpResultEnum.ARTICLE_ALREADY_LIKE);
        }
        return new ResponseResult<>(HttpResultEnum.ARTICLE_UN_LIKE);
    }

    @Override
    public ResponseResult<Object> checkLikedComment(Long commentId) {
        if(isLikedComment(commentId)){
            return new ResponseResult<>(HttpResultEnum.COMMENT_ALREADY_LIKE);
        }
        return new ResponseResult<>(HttpResultEnum.COMMENT_UN_LIKE);
    }

    @Override
    public ResponseResult<Object> cancelLikeArticle(Long articleId) {
        if(!isLikedArticle(articleId)){
            throw new SystemException(HttpResultEnum.FAILED);
        }
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Like::getLikeObjectId, articleId);
        Like like = likeMapper.selectOne(lambdaQueryWrapper);
        likeMapper.deleteById(like);
        return ResponseResult.success();
    }
    @Override
    public ResponseResult<Object> cancelLikeComment(Long commentId) {
        if(!isLikedComment(commentId)){
            return new ResponseResult<>(HttpResultEnum.FAILED);
        }
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Like::getLikeObjectId, commentId);
        Like like = likeMapper.selectOne(lambdaQueryWrapper);
        likeMapper.deleteById(like);
        return ResponseResult.success();
    }

}
