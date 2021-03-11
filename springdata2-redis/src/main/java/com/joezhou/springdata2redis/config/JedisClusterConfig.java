package com.joezhou.springdata2redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JoeZhou
 */
@Configuration
@PropertySource("classpath:jedis.properties")
public class JedisClusterConfig {

    @Value("${cluster.nodes}")
    private String clusterNodes;

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
    public JedisCluster jedisCluster() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setJmxEnabled(true);

        Set<HostAndPort> nodes = new HashSet<>();
        // [[0.0.0.0, 7011], 0.0.0.0:7012, 0.0.0.0:7013, 0.0.0.0:7014, 0.0.0.0:7015, 0.0.0.0:7016]
        String[] servers = clusterNodes.split(",");
        for (String server : servers) {
            String[] kv = server.split(":");
            nodes.add(new HostAndPort(kv[0].trim(), Integer.parseInt(kv[1])));
        }
        return new JedisCluster(nodes, timeout, jedisPoolConfig);
    }
}
