package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JoeZhou
 */
class JedisSentinelTest {

    private JedisPoolConfig jedisPoolConfig;

    void init() {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxWaitMillis(10000L);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMinIdle(0);
    }

    @Test
    void jedisSentinel() {
        init();
        Set<String> sentinels = new HashSet<>();
        sentinels.add("127.0.0.1:27007");
        sentinels.add("127.0.0.1:27008");
        sentinels.add("127.0.0.1:27009");

        try (JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("my-master", sentinels, jedisPoolConfig);
             Jedis jedis = jedisSentinelPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.set("sentinel-key", "sentinel-val");
            System.out.println(jedis.get("sentinel-key"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
