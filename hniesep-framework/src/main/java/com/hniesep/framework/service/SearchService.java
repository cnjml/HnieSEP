package com.hniesep.framework.service;

import com.hniesep.framework.entity.ResponseResult;
import com.hniesep.framework.entity.vo.ArticleListVO;
import com.hniesep.framework.entity.vo.ArticleVO;

import java.util.List;

/**
 * @author HKRR
 */
public interface SearchService {
    /**
     * 搜索文章
     * @return 文章列表
     */
    public ResponseResult<ArticleListVO<List<ArticleVO>>> searchArticle(String keyWord);
}
