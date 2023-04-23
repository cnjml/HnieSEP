package com.hniesep.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hniesep.framework.entity.Board;
import com.hniesep.framework.entity.vo.BoardVO;
import com.hniesep.framework.entity.ResponseResult;

import java.util.List;

/**
 * (Board)表服务接口
 *
 * @author 吉铭炼
 * @since 2023-04-21 17:01:17
 */
public interface BoardService extends IService<Board> {
    /**
     * 获取板块列表
     * @return 板块列表
     */
    ResponseResult<List<BoardVO>> getBroadList();
}
