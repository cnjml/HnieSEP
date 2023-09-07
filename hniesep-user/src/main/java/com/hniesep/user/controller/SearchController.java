package com.hniesep.user.controller;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;
import com.hniesep.framework.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author HKRR
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchServiceImpl searchService;
    @ResponseBody
    @RequestMapping("/{keyword}")
    public ResponseResult<ArticleListVO<List<ArticleVO>>> searchArticle(@PathVariable("keyword") String keyWord){
        return searchService.searchArticle(URLDecoder.decode(keyWord, StandardCharsets.UTF_8));
    }
    @Autowired
    public void setSearchService(SearchServiceImpl searchService){
        this.searchService = searchService;
    }
}
