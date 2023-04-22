package com.hniesep.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author 吉铭炼
 * @since 2023-04-22 12:08:19
 */
@Repository
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
