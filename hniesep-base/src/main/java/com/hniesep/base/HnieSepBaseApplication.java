package com.hniesep.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Debuneko23
 */
@Slf4j
@SpringBootApplication
public class HnieSepBaseApplication {

    public static ConfigurableApplicationContext ac;

    public static void main(String[] args) {

        ac = SpringApplication.run(HnieSepBaseApplication.class, args);
        log.info("http://localhost");

    }

}
