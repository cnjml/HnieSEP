package com.hniesep.user.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 吉铭炼
 */
@Component
public class AppTask {
    @Scheduled(cron = "0/5 * * * * ?")
    public void redisCacheTask(){

    }
}
