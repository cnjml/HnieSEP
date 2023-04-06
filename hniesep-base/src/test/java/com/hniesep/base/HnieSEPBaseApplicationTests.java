package com.hniesep.base;

import com.hniesep.base.account.service.impl.RegisterServiceImpl;
import com.hniesep.base.util.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HnieSEPBaseApplicationTests {
    @Test
    void contextLoads() {
        System.out.printf(System.getProperty("user.dir"));
    }
}