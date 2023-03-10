package com.hniesep.base;

import com.hniesep.base.util.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HnieSEPBaseApplicationTests {
    @Autowired
    MailUtil mailUtil;
    @Test
    void contextLoads() {
        int i=0;
        while (i++<5){
            mailUtil.sendVerificationCode("3204749055@qq.com");
        }
    }
}