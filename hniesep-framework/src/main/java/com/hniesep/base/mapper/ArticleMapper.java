package com.hniesep.base.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.base.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-20 19:56:25
 */
@Mapper
@DS("base")
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 查询所有文章
     *
     * @return 文章列表
     */
    List<Article> selectAll();

    /**
     * 根据作者查询
     *
     * @return 文章列表
     */
    List<Article> selectByAuthor();
    /**
     * 根据关键字查询
     * @return 文章列表
     */
    List<Article> selectByKeyWords();
}
