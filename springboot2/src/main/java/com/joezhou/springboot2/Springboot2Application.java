package com.joezhou.springboot2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;

/**
 * @author JoeZhou
 */
@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@MapperScan("com.joezhou.springboot2.mybatis")
public class Springboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2Application.class, args);
    }

}

/*
@SpringBootApplication
public class Springboot2Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Springboot2Application.class);
    }
}
*/