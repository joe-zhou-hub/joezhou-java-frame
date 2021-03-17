package com.joezhou.springcloud2eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author JoeZhou
 */
@SpringBootApplication
@EnableEurekaServer
public class Springcloud2EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud2EurekaServerApplication.class, args);
	}

}
