package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.Favorite;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.FavoriteArticleListVO;
import com.hniesep.framework.entity.vo.FavoriteArticleVO;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.FavoriteMapper;
import com.hniesep.framework.protocol.FieldCode;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.FavoriteService;
import com.hniesep.framework.util.BeanUtil;
import com.hniesep.framework.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Favorite)表服务实现类
 *
 * @author makejava
 * @since 2023-06-04 13:08:23
 */
@Service("favoriteService")
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
    private final FavoriteMapper favoriteMapper;
    private ArticleServiceImpl articleService;
    @Autowired
    public void setArticleService(ArticleServiceImpl articleService){
        this.articleService = articleService;
    }

    public FavoriteServiceImpl(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public ResponseResult<Object> favoriteArticle(Long articleId) {
        if(isFavoriteArticle(articleId)){
            throw new SystemException(HttpResultEnum.ARTICLE_ALREADY_FAVORITE);
        }
        if(!articleService.isArticleExist(articleId)){
            throw new SystemException(HttpResultEnum.ARTICLE_NOT_EXIST);
        }
        Favorite favorite = new Favorite();
        favorite.setFavoriteObjectId(articleId);
        favorite.setAccountId(SecurityUtil.getAccountId());
        favorite.setFavoriteType(FieldCode.FAVORITE_TYPE_ARTICLE);
        favoriteMapper.insert(favorite);
        return ResponseResult.success();
    }

    public ResponseResult<Object> checkFavoriteArticle(Long articleId){
        if(isFavoriteArticle(articleId)){
            return new ResponseResult<>(HttpResultEnum.ARTICLE_ALREADY_FAVORITE);
        }
        return new ResponseResult<>(HttpResultEnum.ARTICLE_UN_FAVORITE);
    }

    private boolean isFavoriteArticle(Long articleId) {
        LambdaQueryWrapper<Favorite> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Favorite::getFavoriteType, FieldCode.FAVORITE_TYPE_ARTICLE);
        lambdaQueryWrapper.eq(Favorite::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Favorite::getFavoriteObjectId, articleId);
        return favoriteMapper.selectList(lambdaQueryWrapper).size() != 0;
    }
    @Override
    public ResponseResult<Object> cancelFavoriteArticle(Long articleId) {
        if(!isFavoriteArticle(articleId)){
            throw new SystemException(HttpResultEnum.ARTICLE_UN_FAVORITE);
        }
        LambdaQueryWrapper<Favorite> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Favorite::getFavoriteType, FieldCode.FAVORITE_TYPE_ARTICLE);
        lambdaQueryWrapper.eq(Favorite::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Favorite::getFavoriteObjectId, articleId);
        favoriteMapper.deleteById(favoriteMapper.selectOne(lambdaQueryWrapper));
        return ResponseResult.success();
    }
    @Override
    public ResponseResult<FavoriteArticleListVO<List<FavoriteArticleVO>>> favoriteArticleList(){
        FavoriteArticleListVO<List<FavoriteArticleVO>> favoriteArticleListVO = new FavoriteArticleListVO<>();
        LambdaQueryWrapper<Favorite> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Favorite::getAccountId,SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Favorite::getFavoriteType,FieldCode.FAVORITE_TYPE_ARTICLE);
        List<FavoriteArticleVO> favorites = BeanUtil.copyBeanList(favoriteMapper.selectList(lambdaQueryWrapper), FavoriteArticleVO.class);
        for(FavoriteArticleVO articleVO : favorites){
            String articleTitle = articleService.getTitleById(articleVO.getFavoriteObjectId());
            articleVO.setArticleTitle(articleTitle);
        }
        favoriteArticleListVO.setTotal(favorites.size());
        favoriteArticleListVO.setRows(favorites);
        return ResponseResult.success(favoriteArticleListVO);
    }
}
