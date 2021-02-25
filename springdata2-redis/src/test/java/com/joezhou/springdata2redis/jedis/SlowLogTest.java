package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JoeZhou
 */
class SlowLogTest {

    private JedisPoolConfig jedisPoolConfig;

    void init() {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxWaitMillis(10000L);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMinIdle(0);
    }

    @Test
    void slowlog() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            System.out.println("slowlog get: " + jedis.slowlogGet(2));
            System.out.println("slowlog len: " + jedis.slowlogLen());
            System.out.println("slowlog reset: " + jedis.slowlogReset());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
