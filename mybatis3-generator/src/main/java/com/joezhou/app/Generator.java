package com.joezhou.app;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JoeZhou
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser parser = new ConfigurationParser(warnings);
        File generatorConfigPath = new File("mybatis3-generator/generator.xml");
        Configuration config = parser.parseConfiguration(generatorConfigPath);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
        System.out.println("generate over...");
    }
}