package com.hniesep.base;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class HnieSEPBaseApplicationTests {
    @Test
    void generate() {

        //数据源配置
        String url = "jdbc:mysql://43.206.126.181:33066/base";
        String username = "root";
        String password = "Hni1sep-backend";
        //Javadoc作者
        String author = "吉铭炼";
        //是否输出到main, 默认输出到test
        boolean outToMain = false;
        //全局输出路径配置
        String projectPath = System.getProperty("user.dir") + "/";
        String outPutDir = outToMain ? projectPath + "src/main/java/" : projectPath + "src/test/java/";
        //mapper生成路径
        String modulePath = "com/hniesep/base";
        String pathInfo = outToMain ? projectPath + "src/main/resources/" + modulePath + "/mapper" : projectPath + "src/test/resources/" + modulePath + "/mapper";
        //设置需要生成的表名
        List<String> tables = new ArrayList<>();
        tables.add("t_article");
        tables.add("t_comment");
        tables.add("t_setting");
        tables.add("t_board");
        //表前缀过滤
        String prefix = "t_";
        //设置父包名
        String parent = "com.hniesep";
        //设置子包名
        String moduleName = "base";


        //代码生成器
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder.author(author)
                        .disableOpenDir()
                        .outputDir(outPutDir))
                .packageConfig(builder -> builder.parent(parent)
                        .moduleName(moduleName)
                        .pathInfo(Collections.singletonMap(OutputFile.xml, pathInfo)))
                .strategyConfig(builder -> builder.addInclude(tables)
                        .addTablePrefix(prefix))
                .execute();
    }
}