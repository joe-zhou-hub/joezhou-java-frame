package com.joezhou.springboot2activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @author JoeZhou
 */
@SpringBootApplication
@EnableJms
public class Springboot2ActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2ActivemqApplication.class, args);
	}
}
