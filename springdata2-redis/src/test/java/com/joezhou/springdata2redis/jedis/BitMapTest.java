package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JoeZhou
 */
class BitMapTest {

    private JedisPoolConfig jedisPoolConfig;

    void init() {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxWaitMillis(10000L);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMinIdle(0);
    }

    @Test
    void getBit() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.set("name", "a");
            System.out.print("name-bitmap: ");
            for (long i = 0, j = jedis.strlen("name") * 8; i < j; i++) {
                System.out.print(jedis.getbit("name", i) ? 1 : 0);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setBit() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.set("name", "a");
            System.out.print("name-bitmap: ");
            for (long i = 0, j = jedis.strlen("name") * 8; i < j; i++) {
                System.out.print(jedis.getbit("name", i) ? 1 : 0);
            }
            System.out.println();

            jedis.setbit("name", 0, true);
            jedis.setbit("name", 1, false);
            jedis.setbit("name", 2, true);
            System.out.print("name-bitmap: ");
            for (long i = 0, j = jedis.strlen("name") * 8; i < j; i++) {
                System.out.print(jedis.getbit("name", i) ? 1 : 0);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void bitCount() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.set("name", "a");
            System.out.print("name-bitmap: ");
            for (long i = 0, j = jedis.strlen("name") * 8; i < j; i++) {
                System.out.print(jedis.getbit("name", i) ? 1 : 0);
            }
            System.out.println();
            System.out.println(jedis.bitcount("name", 0, 9));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void bitop() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.set("k1", "a");
            System.out.print("k1-bitmap: ");
            for (long i = 0, j = jedis.strlen("k1") * 8; i < j; i++) {
                System.out.print(jedis.getbit("k1", i) ? 1 : 0);
            }
            System.out.println();

            jedis.set("k2", "b");
            System.out.print("k2-bitmap: ");
            for (long i = 0, j = jedis.strlen("k2") * 8; i < j; i++) {
                System.out.print(jedis.getbit("k2", i) ? 1 : 0);
            }
            System.out.println();

            jedis.bitop(BitOP.XOR, "k3", "k2", "k1");
            System.out.print("k3-bitmap: ");
            for (long i = 0, j = jedis.strlen("k3") * 8; i < j; i++) {
                System.out.print(jedis.getbit("k3", i) ? 1 : 0);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void bitpos() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.set("name", "a");
            System.out.print("name-bitmap: ");
            for (long i = 0, j = jedis.strlen("name") * 8; i < j; i++) {
                System.out.print(jedis.getbit("name", i) ? 1 : 0);
            }
            System.out.println();

            System.out.println("the index of first 0 in bitmap is: " + jedis.bitpos("name", false));
            System.out.println("the index of first 1 in bitmap is: " + jedis.bitpos("name", true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
