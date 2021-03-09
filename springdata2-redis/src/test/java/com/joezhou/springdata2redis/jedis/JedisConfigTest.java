package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author JoeZhou
 */
@SpringBootTest
class JedisConfigTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    void jedisConfig() {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex("name", 5, "100");
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
