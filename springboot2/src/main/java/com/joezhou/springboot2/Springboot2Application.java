package com.joezhou.springboot2;

import com.joezhou.springboot2.servlet.ContextServlet;
import com.joezhou.springboot2.servlet.filter.ContextFilter;
import com.joezhou.springboot2.servlet.listener.ContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import java.util.EnumSet;

/**
 * @author JoeZhou
 */
@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@ServletComponentScan("com.joezhou.springboot2.servlet")
/*@EnableScheduling*/
@EnableAsync
public class Springboot2Application implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2Application.class, args);
    }

    @Override
    public void onStartup(ServletContext context) {

        context.addServlet("contextServlet", new ContextServlet())
                .addMapping("/api/servlet/contextServlet");

        context.addFilter("contextFilter", new ContextFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,
                        "/api/servlet/*");

        context.addListener(new ContextListener());
    }
}

/*
@SpringBootApplication
public class Springboot2Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Springboot2Application.class);
    }
}*/
