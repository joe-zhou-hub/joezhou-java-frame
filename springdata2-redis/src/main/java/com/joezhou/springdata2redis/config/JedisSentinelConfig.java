package com.joezhou.springdata2redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author JoeZhou
 */
@Configuration
@PropertySource("classpath:jedis.properties")
public class JedisSentinelConfig {

    @Value("${sentinel.nodes}")
    private String sentinelNodes;

    @Value("${sentinel.master}")
    private String master;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${pool.max-idle}")
    private int maxIdle;

    @Value("${pool.min-idle}")
    private int minIdle;

    @Value("${pool.max-active}")
    private int maxActive;

    @Value("${pool.max-wait}")
    private long maxWait;

    @Bean
    public JedisSentinelPool jedisSentinelPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setJmxEnabled(true);

        String[] servers = sentinelNodes.split(",");
        Set<String> sentinels = new HashSet<>(Arrays.asList(servers));
        return new JedisSentinelPool(master, sentinels, jedisPoolConfig, timeout);
    }
}
