package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.Account;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.CommentBO;
import com.hniesep.framework.entity.vo.CommentListVO;
import com.hniesep.framework.entity.vo.CommentVO;
import com.hniesep.framework.exception.SystemException;
import com.hniesep.framework.mapper.CommentMapper;
import com.hniesep.framework.entity.Comment;
import com.hniesep.framework.protocol.FieldCode;
import com.hniesep.framework.protocol.FieldMessage;
import com.hniesep.framework.protocol.HttpResultEnum;
import com.hniesep.framework.service.CommentService;
import com.hniesep.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    AccountServiceImpl accountService;
    @Autowired
    public void setAccountService(AccountServiceImpl accountService){
        this.accountService = accountService;
    }
    @Override
    public ResponseResult<CommentListVO<List<CommentVO>>> commentList(Integer articleId, Integer pageIndex, Integer pageSize){
        if(pageIndex==null||pageSize==null||articleId==null){
            throw new SystemException(HttpResultEnum.ARGUMENTS_ERROR);
        }
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询该文章下的评论
        commentLambdaQueryWrapper.eq(Comment::getArticleId,articleId);
        //查询的必须是根评论
        commentLambdaQueryWrapper.eq(Comment::getCommentParentId,FieldCode.COMMENT_IS_ROOT);
        //通过审核的评论
        commentLambdaQueryWrapper.eq(Comment::getCommentStatus, FieldCode.COMMENT_STATUS_PASS);
        commentLambdaQueryWrapper.orderByAsc(Comment::getCommentTime);
        Page<Comment> page = new Page<>(pageIndex,pageSize);
        page(page,commentLambdaQueryWrapper);
        //获取评论列表
        List<CommentVO> comments = toCommentVOList(page.getRecords());
        CommentListVO<List<CommentVO>> commentListVO = new CommentListVO<>();
        //查询子评论
        for(CommentVO comment:comments){
            List<CommentVO> children = getChildren(comment.getCommentId());
            //set子评论
            if(Objects.nonNull(children)){
                comment.setChildren(children);
            }
        }
        //设置评论列表对象的rows和total
        commentListVO.setRows(comments);
        commentListVO.setTotal(page.getTotal());
        return ResponseResult.success(commentListVO);
    }
    private List<CommentVO> toCommentVOList(List<Comment> list){
        List<CommentVO> comments = BeanUtil.copyBeanList(list, CommentVO.class);
        for (CommentVO  commentVO:comments){
            //获取根评论对象
            Account account = accountService.getById(commentVO.getAccountId());
            //设置根评论昵称
            String nickName = Objects.isNull(account)? FieldMessage.ACCOUNT_NOT_EXIST : account.getAccountNickName();
            commentVO.setAccountNickName(nickName);
            //如果不是根评论
            if(commentVO.getCommentParentId()!=0){
                //获取回复对象的昵称
                account = accountService.getById(commentVO.getCommentToAccountId());
                nickName = Objects.isNull(account)? FieldMessage.ACCOUNT_NOT_EXIST : account.getAccountNickName();
                commentVO.setCommentToAccountNickName(nickName);
            }
        }
        return comments;
    }
    private List<CommentVO> getChildren(Long commentId){
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getCommentParentId,commentId);
        lambdaQueryWrapper.orderByAsc(Comment::getCommentTime);
        List<Comment> comments = list(lambdaQueryWrapper);
        return toCommentVOList(comments);
    }
    @Override
    public ResponseResult<Object> comment(CommentBO commentBO) {
//        commentService.save();
        return null;
    }
}
