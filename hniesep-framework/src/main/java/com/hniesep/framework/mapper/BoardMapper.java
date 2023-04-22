package com.hniesep.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Board;
import org.springframework.stereotype.Repository;

/**
 * (Board)表数据库访问层
 *
 * @author 吉铭炼
 * @since 2023-04-21 17:03:35
 */
@Repository
public interface BoardMapper extends BaseMapper<Board> {

}

