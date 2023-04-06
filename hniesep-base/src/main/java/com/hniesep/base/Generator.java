package com.hniesep.base;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import org.springframework.stereotype.Component;
import java.util.Collections;

/**
 * @author 吉铭炼
 */
@Component
public class Generator {
    public static void main(String[] args) {
        //数据源配置
        String url = "jdbc:mysql://43.206.126.181:33066/base";
        String username = "root";
        String password = "Hni1sep-backend";
        String author = "吉铭炼";
        String projectPath = System.getProperty("user.dir");
        String parent = "com.hniesep";
        String moduleName = "hniesep-base";
        String pathInfo = projectPath + "src/main/resources/";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath+"src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, pathInfo)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_article","t_setting","t_board","t_comment") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .execute();
    }
}