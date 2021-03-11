package com.joezhou.springdata2redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JoeZhou
 */
@Configuration
@PropertySource("classpath:jedis.properties")
public class JedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.password}")
    private String password;

    @Value("${pool.max-idle}")
    private int maxIdle;

    @Value("${pool.min-idle}")
    private int minIdle;

    @Value("${pool.max-active}")
    private int maxActive;

    @Value("${pool.max-wait}")
    private long maxWait;

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setJmxEnabled(true);
        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }
}
