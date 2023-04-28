package com.hniesep.user.task;

import com.hniesep.framework.entity.Article;
import com.hniesep.framework.service.ArticleService;
import com.hniesep.framework.service.impl.ArticleServiceImpl;
import com.hniesep.framework.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 吉铭炼
 */
@Component
public class AppTask {
    private RedisCache redisCache;
    private ArticleService articleService;
    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }
    @Autowired
    public void setRedisCache(RedisCache redisCache){
        this.redisCache = redisCache;
    }
    @Scheduled(cron = "0/5 * * * * ?")
    public void redisCacheTask(){
        //获取redis中的浏览量
        Map<String,Integer> readsMap = redisCache.getCacheMap("article:reads");
        List<Article> articles = readsMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()) ,entry.getValue()))
                .toList();
        //更新到数据库中
        articleService.updateBatchById(articles);
    }

}
