package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JoeZhou
 */
class JedisPoolTest {

    @Test
    void jedisPool() {
        try (JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {
            jedis.set("jedis-pool-name", "jedis-pool-value");
            System.out.println(jedis.get("jedis-pool-name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
