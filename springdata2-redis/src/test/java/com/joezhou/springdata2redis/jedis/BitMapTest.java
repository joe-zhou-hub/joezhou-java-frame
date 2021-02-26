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

    /***
     * 2021-01-21日活跃用户3人，id分别为09，15，17
     * 2021-01-22日活跃用户4人，id分别为09，17，19，52
     * 2021-01-23日活跃用户5人，id分别为09，52，68
     * 使用bitmap统计用户09在这三天内登录了几次。
     */
    @Test
    void loginTimesDemo() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            // clear data
            jedis.del("user09-2021");
            jedis.setbit("user09-2021", 21, true);
            jedis.setbit("user09-2021", 22, true);
            jedis.setbit("user09-2021", 23, true);
            System.out.println(jedis.bitcount("user09-2021"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 2021-01-21日活跃用户3人，id分别为09，15，17
     * 2021-01-22日活跃用户4人，id分别为09，17，19，52
     * 2021-01-23日活跃用户5人，id分别为09，52，68
     * 使用bitmap统计这三天内的活跃用户人数。
     */
    @Test
    void activeUserCountDemo() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            // clear data
            jedis.del("2021-01-21", "2021-01-22", "2021-01-23", "result");

            jedis.setbit("2021-01-21", 9, true);
            jedis.setbit("2021-01-21", 15, true);
            jedis.setbit("2021-01-21", 17, true);

            jedis.setbit("2021-01-22", 9, true);
            jedis.setbit("2021-01-22", 17, true);
            jedis.setbit("2021-01-22", 19, true);
            jedis.setbit("2021-01-22", 52, true);

            jedis.setbit("2021-01-23", 9, true);
            jedis.setbit("2021-01-23", 52, true);
            jedis.setbit("2021-01-23", 68, true);

            jedis.bitop(BitOP.OR, "result", "2021-01-21", "2021-01-22", "2021-01-23");
            System.out.println(jedis.bitcount("result"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
