package com.joezhou.app;

import org.mybatis.generator.api.MyBatisGenerator;
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
        new MyBatisGenerator(new ConfigurationParser(warnings).parseConfiguration(
                new File("mybatis3-generator/generator.xml")),
                new DefaultShellCallback(true), warnings)
                .generate(null);
        System.out.println("generate over...");
    }
}