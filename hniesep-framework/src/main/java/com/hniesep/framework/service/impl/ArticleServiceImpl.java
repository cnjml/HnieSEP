package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.*;
import com.hniesep.framework.entity.bo.ArticleBO;
import com.hniesep.framework.entity.vo.ArticleDetailVO;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.ArticleMapper;
import com.hniesep.framework.mapper.LikeMapper;
import com.hniesep.framework.protocol.FieldCode;
import com.hniesep.framework.protocol.FieldMessage;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.ArticleService;
import com.hniesep.framework.util.BeanUtil;
import com.hniesep.framework.util.DateTimeUtil;
import com.hniesep.framework.util.RedisCache;
import com.hniesep.framework.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    private final ArticleMapper articleMapper;
    private final LikeMapper likeMapper;
    private RedisCache redisCache;
    private AccountServiceImpl accountService;
    private SettingServiceImpl settingService;
    private CommentServiceImpl commentService;

    @Autowired
    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setSettingService(SettingServiceImpl settingService) {
        this.settingService = settingService;
    }

    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Autowired
    public void setCommentService(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    public ArticleServiceImpl(ArticleMapper articleMapper, LikeMapper likeMapper) {
        this.articleMapper = articleMapper;
        this.likeMapper = likeMapper;
    }

    @Override
    public ResponseResult<List<ArticleVO>> popularArticles() {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getArticleAudit, FieldCode.ARTICLE_AUDIT_PASS);
        articleLambdaQueryWrapper.eq(Article::getArticleRelease, FieldCode.ARTICLE_RELEASE_PUBLISH);
        articleLambdaQueryWrapper.orderByAsc(Article::getArticleReads);
        Page<Article> page = new Page<>(1, 10);
        page(page, articleLambdaQueryWrapper);
        List<Article> articles = page.getRecords();
        List<ArticleVO> popularArticles = BeanUtil.copyBeanList(articles, ArticleVO.class);
        return ResponseResult.success(popularArticles);
    }

    @Override
    public ResponseResult<ArticleListVO<List<ArticleVO>>> articleList(Integer pageIndex, Integer pageSize, Long boardId) {
        if (pageIndex == null || pageSize == null || boardId == null) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getArticleAudit, FieldCode.ARTICLE_AUDIT_PASS);
        articleLambdaQueryWrapper.eq(Article::getArticleRelease, FieldCode.ARTICLE_RELEASE_PUBLISH);
        articleLambdaQueryWrapper.eq(boardId > 0, Article::getBoardId, boardId);
        Page<Article> page = new Page<>(pageIndex, pageSize);
        page(page, articleLambdaQueryWrapper);
        ArticleListVO<List<ArticleVO>> articleListVO = new ArticleListVO<>();
        List<ArticleVO> articles = BeanUtil.copyBeanList(page.getRecords(), ArticleVO.class);
        for (ArticleVO articleVO : articles) {
            Account account = accountService.getById(articleVO.getAccountId());
            articleVO.setAccountNickname(account.getAccountNickname());
            articleVO.setArticleLikes(getLikes(articleVO.getArticleId()));
        }
        articleListVO.setRows(articles);
        articleListVO.setTotal(page.getTotal());
        return ResponseResult.success(articleListVO);
    }

    @Override
    public ResponseResult<ArticleDetailVO> articleDetail(Long articleId) {
        if (articleId == null) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getArticleId, articleId);
        articleLambdaQueryWrapper.eq(Article::getArticleAudit, FieldCode.ARTICLE_AUDIT_PASS);
        articleLambdaQueryWrapper.eq(Article::getArticleRelease, FieldCode.ARTICLE_RELEASE_PUBLISH);
        Article article = articleMapper.selectOne(articleLambdaQueryWrapper);
        ArticleDetailVO articleDetailVO = BeanUtil.copyBean(articleMapper.selectOne(articleLambdaQueryWrapper), ArticleDetailVO.class);
        //增加浏览量到redis
        updateReads(articleId);
        //从redis中获取浏览量
        Integer reads = redisCache.getCacheMapValue("article:reads", articleId.toString());
        //设置redis中返回的浏览量
        articleDetailVO.setArticleReads(reads);
        articleDetailVO.setArticleLikes(getLikes(articleId));
        articleDetailVO.setAccountNickname(accountService.getById(article.getAccountId()).getAccountNickname());
        articleDetailVO.setAccountAvatar(accountService.getById(article.getAccountId()).getAccountAvatar());
        return ResponseResult.success(articleDetailVO);
    }

    @Override
    public ResponseResult<Object> updateReads(Long articleId) {
        redisCache.incrementCacheMapValue("article:reads", articleId.toString(), 1);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> addArticle(ArticleBO articleBO) {
        String title = articleBO.getTitle();
        String content = articleBO.getContent();
        String topic = articleBO.getTopic();
        Long boardId = articleBO.getBoardId();
        Integer release = articleBO.getRelease();
        if (!StringUtils.hasText(title) || !StringUtils.hasText(content)) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        if (release != 0 && release != 1) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        Article article = new Article();
        article.setArticleContent(content);
        article.setArticleTitle(title);
        article.setArticleTopic(topic);
        article.setBoardId(boardId);
        article.setAccountId(SecurityUtil.getAccountId());
        article.setArticleRelease(release);
        article.setArticleAudit(settingService.isArticleNeedAudit() ? FieldCode.ARTICLE_AUDIT_WAIT : FieldCode.ARTICLE_AUDIT_PASS);
        articleMapper.insert(article);
        return ResponseResult.success();
    }

    private Integer getLikes(Long articleId) {
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getLikeType, FieldCode.LIKE_ARTICLE);
        lambdaQueryWrapper.eq(Like::getLikeObjectId, articleId);
        return likeMapper.selectList(lambdaQueryWrapper).size();
    }

    @Override
    public String getTitleById(Long articleId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getArticleId, articleId);
        return articleMapper.selectOne(lambdaQueryWrapper) != null ? articleMapper.selectOne(lambdaQueryWrapper).getArticleTitle() : FieldMessage.ARTICLE_NOT_EXIST;
    }

    @Override
    public ResponseResult<Object> updateArticle(ArticleBO articleBO) {
        if (!StringUtils.hasText(articleBO.getContent())) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Article::getArticleId, articleBO.getArticleId());
        Article article = articleMapper.selectOne(lambdaQueryWrapper);
        article.setArticleContent(articleBO.getContent());
        article.setArticleUpdateTime(DateTimeUtil.getDateTime());
        articleMapper.updateById(article);
        return ResponseResult.success();
    }

    public boolean isArticleExist(Long articleId) {
        return articleMapper.selectById(articleId) != null;
    }

    @Override
    public ResponseResult<Object> deleteArticle(ArticleBO articleBO) {
        if (articleBO.getArticleId() == null || !isArticleExist(articleBO.getArticleId())) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getAccountId, SecurityUtil.getAccountId());
        lambdaQueryWrapper.eq(Article::getArticleId, articleBO.getArticleId());
        Article article = articleMapper.selectOne(lambdaQueryWrapper);
        if (article == null) {
            throw new SystemException(HttpResultEnum.NO_PERMISSION);
        }
        List<Comment> comments = commentService.getAllComments(article.getArticleId());
        //原子性,删除文章下的评论
        for (Comment comment:comments){
            commentService.deleteCommentById(comment.getCommentId());
        }
        articleMapper.deleteById(article);
        //原子性
        return ResponseResult.success();
    }

}
