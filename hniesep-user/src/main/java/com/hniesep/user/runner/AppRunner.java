package com.hniesep.user.runner;

import com.hniesep.framework.entity.Article;
import com.hniesep.framework.mapper.ArticleMapper;
import com.hniesep.framework.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 吉铭炼
 */
@Component
public class AppRunner implements CommandLineRunner {
    private ArticleMapper articleMapper;
    private RedisCache redisCache;
    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
    @Autowired
    public void setRedisCache(RedisCache redisCache){
        this.redisCache = redisCache;
    }
    @Override
    public void run(String... args) throws Exception {
        //查询文章信息：id，reads
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> readsMap = articles.stream().collect(Collectors.toMap(article -> article.getArticleId().toString(), Article::getArticleReads));
        //存储到redis中
        redisCache.setCacheMap("reads",readsMap);
    }

}
