package com.hniesep.base;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HnieSEPBaseApplicationTests {
    @Test
    void contextLoads() {
        System.out.printf(System.getProperty("user.dir"));
    }
}