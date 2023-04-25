package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Comment;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.bo.CommentBO;
import com.hniesep.framework.entity.vo.CommentListVO;
import com.hniesep.framework.entity.vo.CommentVO;

import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2023-04-24 11:12:16
 */
public interface CommentService extends IService<Comment> {
    /**
     * 获取评论列表
     * @param articleId 文章id
     * @param pageIndex 页码
     * @param pageSize 页面大小
     * @return 评论列表
     */
    ResponseResult<CommentListVO<List<CommentVO>>> commentList(Integer articleId, Integer pageIndex, Integer pageSize);

    /**
     * 发送评论
     * @param commentBO 评论业务对象
     * @return 响应结果
     */
    ResponseResult<Object> comment(CommentBO commentBO);
}
