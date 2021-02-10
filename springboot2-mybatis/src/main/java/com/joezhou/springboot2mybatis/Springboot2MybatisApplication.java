package com.joezhou.springboot2mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JoeZhou
 */
@SpringBootApplication
@MapperScan("com.joezhou.springboot2mybatis.mapper")
public class Springboot2MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2MybatisApplication.class, args);
	}

}
