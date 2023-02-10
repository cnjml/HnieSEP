package com.hniesep.base.mapper;

import com.hniesep.base.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author 吉铭炼
 */
@Mapper
public interface ArticleMapper {
    /**
     * 查询所有文章
     * @return 文章列表
     */
    List<Article> selectAll();

    /**
     * 根据作者查询
     * @return 文章列表
     */
    List<Article> selectByAuthor();

    /**
     * 根据关键字查询
     * @return 文章列表
     */
    List<Article> selectByKeyWords();



}