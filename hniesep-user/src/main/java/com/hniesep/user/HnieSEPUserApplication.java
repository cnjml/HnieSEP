package com.hniesep.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 吉铭炼
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.hniesep.framework","com.hniesep.user"})
@MapperScan("com.hniesep.framework.mapper")
public class HnieSEPUserApplication {
    public static ConfigurableApplicationContext ac;
    public static void main(String[] args) {
        ac = SpringApplication.run(HnieSEPUserApplication.class, args);
        log.info("http://localhost:8080");
    }
}