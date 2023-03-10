package com.hniesep.base;

import com.hniesep.base.util.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HniesepBaseApplicationTests {
    @Autowired
    MailUtil mailUtil;
    @Test
    void contextLoads() {
        mailUtil.sendVerificationCode("2918208425@qq.com");
    }
}