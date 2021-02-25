package com.joezhou.springdata2redis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JoeZhou
 */
public class SubscriberA {

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

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            System.out.println("channels: " + jedis.pubsubChannels("*"));
            System.out.println("subscribers: " + jedis.pubsubNumSub("sina"));
            jedis.subscribe(new SubscriberListener("sub-a"), "sohu", "sina");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
