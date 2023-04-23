package com.hniesep.user.controller;

import com.hniesep.framework.entity.vo.BoardVO;
import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.service.impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 吉铭炼
 */
@Controller
@RequestMapping("/board")
public class BroadController {
    private BoardServiceImpl boardService;
    @Autowired
    public void setBroadService(BoardServiceImpl boardService){
        this.boardService = boardService;
    }
    @GetMapping("/getBoardList")
    @ResponseBody
    public ResponseResult<List<BoardVO>> getBoardList(){
        return boardService.getBroadList();
    }
}
