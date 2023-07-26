package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.Comment;
import com.hniesep.framework.entity.Like;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.CommentBO;
import com.hniesep.framework.entity.vo.CommentListVO;
import com.hniesep.framework.entity.vo.CommentVO;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.CommentMapper;
import com.hniesep.framework.mapper.LikeMapper;
import com.hniesep.framework.protocol.FieldCode;
import com.hniesep.framework.protocol.FieldMessage;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.CommentService;
import com.hniesep.framework.util.BeanUtil;
import com.hniesep.framework.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-04-24 11:15:41
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    private final LikeMapper likeMapper;
    private final CommentMapper commentMapper;
    private AccountServiceImpl accountService;
    private LikeServiceImpl likeService;
    public CommentServiceImpl(LikeMapper likeMapper,CommentMapper commentMapper) {
        this.likeMapper = likeMapper;
        this.commentMapper = commentMapper;
    }
    @Autowired
    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }
    @Autowired
    public void setLikeService(LikeServiceImpl likeService){
        this.likeService = likeService;
    }

    @Override
    public ResponseResult<CommentListVO<List<CommentVO>>> commentList(Long articleId, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageSize == null || articleId == null) {
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询该文章下的评论
        commentLambdaQueryWrapper.eq(Comment::getArticleId, articleId);
        //查询的必须是根评论
        commentLambdaQueryWrapper.eq(Comment::getCommentParentId, FieldCode.COMMENT_IS_ROOT);
        //通过审核的评论
        commentLambdaQueryWrapper.eq(Comment::getCommentStatus, FieldCode.COMMENT_STATUS_PASS);
        commentLambdaQueryWrapper.orderByAsc(Comment::getCommentTime);
        Page<Comment> page = new Page<>(pageIndex, pageSize);
        page(page, commentLambdaQueryWrapper);
        //获取评论列表
        List<CommentVO> comments = toCommentVOList(page.getRecords());
        CommentListVO<List<CommentVO>> commentListVO = new CommentListVO<>();
        //查询子评论
        int childrenTotal = 0;
        for (CommentVO commentVO : comments) {
            List<CommentVO> children = getChildren(commentVO.getCommentId());
            //set子评论
            if (Objects.nonNull(children)) {
                commentVO.setChildren(children);
            }
            childrenTotal += children.size();
        }
        //设置评论列表对象的rows和total
        commentListVO.setRows(comments);
        commentListVO.setTotal(page.getTotal() + childrenTotal);
        return ResponseResult.success(commentListVO);
    }

    private List<CommentVO> getChildren(Long commentId) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getCommentParentId, commentId);
        lambdaQueryWrapper.orderByAsc(Comment::getCommentTime);
        List<Comment> comments = list(lambdaQueryWrapper);
        return toCommentVOList(comments);
    }
    private List<CommentVO> toCommentVOList(List<Comment> list) {
        List<CommentVO> comments = BeanUtil.copyBeanList(list, CommentVO.class);
        for (CommentVO commentVO : comments) {
            //获取根评论对象
            Account account = accountService.getById(commentVO.getAccountId());
            //设置根评论昵称
            String nickName = Objects.isNull(account) ? FieldMessage.ACCOUNT_NOT_EXIST : account.getAccountNickname();
            commentVO.setAccountNickname(nickName);
            //设置评论喜欢数
            commentVO.setCommentLikes(getCommentLikes(commentVO.getCommentId()));
            //如果不是根评论
            if (commentVO.getCommentParentId() != 0) {
                //获取回复对象的昵称
                account = accountService.getById(commentVO.getCommentToAccountId());
                //如果回复的对象注销则设置“账户已注销”
                nickName = Objects.isNull(account) ? FieldMessage.ACCOUNT_NOT_EXIST : account.getAccountNickname();
                commentVO.setCommentToAccountNickname(nickName);
            }
            try {
                //动态：当前请求用户是否喜欢
                Long accountId = SecurityUtil.getAccountId();
                boolean isLiked = likeService.isCommentLiked(commentVO.getCommentId(),accountId);
                commentVO.setCommentAccountLike(isLiked?FieldCode.COMMENT_LIKED:FieldCode.COMMENT_UN_LIKED);
            }catch (Exception e){
                System.out.println("匿名用户请求评论列表");
            }
        }
        return comments;
    }
    @Override
    public ResponseResult<Object> addComment(CommentBO commentBO) {
        //内容未空
        if (!StringUtils.hasText(commentBO.getCommentContent())) {
            throw new SystemException(HttpResultEnum.CONTENT_IS_NULL);
        }
        Comment comment = BeanUtil.copyBean(commentBO, Comment.class);
        commentMapper.insert(comment);
        return ResponseResult.success();
    }

    private Integer getCommentLikes(Long commentId){
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getLikeType,FieldCode.LIKE_COMMENT);
        lambdaQueryWrapper.eq(Like::getLikeObjectId, commentId);
        return likeMapper.selectList(lambdaQueryWrapper).size();
    }
}
