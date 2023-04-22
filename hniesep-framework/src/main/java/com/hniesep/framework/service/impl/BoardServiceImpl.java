package com.hniesep.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniesep.framework.entity.Board;
import com.hniesep.framework.entity.vo.BoardVO;
import com.hniesep.framework.entity.vo.ResponseResult;
import com.hniesep.framework.mapper.BoardMapper;
import com.hniesep.framework.service.BoardService;
import com.hniesep.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Board)表服务实现类
 *
 * @author makejava
 * @since 2023-04-21 17:02:21
 */
@Service("boardService")
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board> implements BoardService {
    private BoardMapper boardMapper;
    @Autowired
    public void setBoardMapper(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }
    @Override
    public ResponseResult<List<BoardVO>> getBroadList() {
        List<BoardVO> boards = BeanUtil.copyBeanList(boardMapper.selectList(null),BoardVO.class);
        return ResponseResult.success(boards);
    }
}

