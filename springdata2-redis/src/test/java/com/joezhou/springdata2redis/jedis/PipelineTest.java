package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * @author JoeZhou
 */
class PipelineTest {

    private JedisPoolConfig jedisPoolConfig;

    void init() {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxWaitMillis(10000L);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMinIdle(0);
    }

    @Test
    void noPipeline() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            long startTime = System.currentTimeMillis();
            // no pipeline
            // total spend = 10000 * (netTime) + 10000 * (commandTime)
            // carry 1 command per send
            for (int i = 0; i < 10000; i++) {
                jedis.hset("key" + i, "field" + i, "value" + i);
            }
            System.out.println("hset with no pipeline done: " + (System.currentTimeMillis() - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void pipeline() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if ("PONG".equals(jedis.ping())) {
                long startTime = System.currentTimeMillis();
                // pipeline
                // total spend = 10 * (netTime) + 1000 * (commandTime)
                // carry 100 command per send
                for (int i = 0; i < 10; i++) {
                    Pipeline pipeline = jedis.pipelined();
                    // 0-99, 100-199, 200-299...
                    for (int j = i * 100; j < (i + 1) * 100; j++) {
                        pipeline.hset("key" + i, "field" + i, "value" + i);
                    }
                    pipeline.syncAndReturnAll();
                }
                System.out.println("hset with no pipeline done: " + (System.currentTimeMillis() - startTime));
            } else {
                System.out.println("ping error...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
