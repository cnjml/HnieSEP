package com.hniesep.base;

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
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(url,username,password);
        DataSourceConfig dataSourceConfig = dataSourceConfigBuilder.build();
        //全局配置
        String author = "吉铭炼";
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .disableOpenDir()
                .outputDir(projectPath+"src/main/java/")
                .author(author)
                .build();
        //包配置
        String parent = "com.hniesep";
        String moduleName = "hniesep-base";
        String pathInfo = projectPath + "src/main/resources/";
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent(parent)
                .moduleName(moduleName)
                .pathInfo(Collections.singletonMap(OutputFile.xml, pathInfo))
                .build();
        //模板配置
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .build();

    }
}