package com.hniesep.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-24 10:58:06
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}

