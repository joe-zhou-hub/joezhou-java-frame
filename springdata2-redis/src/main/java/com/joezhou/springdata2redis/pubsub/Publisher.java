package com.joezhou.springdata2redis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JoeZhou
 */
public class Publisher {
    private static JedisPoolConfig jedisPoolConfig;

    static {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxWaitMillis(10000L);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMinIdle(0);
    }

    public static void main(String[] args) {

        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if ("PONG".equals(jedis.ping())) {
                jedis.publish("sohu", "hi sohu");
                jedis.publish("sina", "hi sina");
                System.out.println("publish msessage over...");
            } else {
                System.out.println("ping error...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
