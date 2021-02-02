package com.joezhou.springboot2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author JoeZhou
 */
/*@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@MapperScan("com.joezhou.springboot2.mybatis.mapper")
public class Springboot2Application {
    public static void main(String[] args) {
        SpringApplication.run(Springboot2Application.class, args);
    }
}*/

@SpringBootApplication
public class Springboot2Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Springboot2Application.class);
    }
}