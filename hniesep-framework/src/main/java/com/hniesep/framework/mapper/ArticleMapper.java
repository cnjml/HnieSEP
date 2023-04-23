package com.hniesep.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniesep.framework.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Article)表数据库访问层
 *
 * @author 吉铭炼
 * @since 2023-04-22 12:08:19
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
