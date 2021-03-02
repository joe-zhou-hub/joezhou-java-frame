package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;

/**
 * @author JoeZhou
 */
class GeoTest {

    private JedisPoolConfig jedisPoolConfig;

    void init() {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxWaitMillis(10000L);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMinIdle(0);
    }

    @Test
    void geoadd() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.geoadd("city", 116.28, 39.55, "beijing");
            jedis.geoadd("city", 117.12, 39.08, "tianjin");
            jedis.geoadd("city", 114.29, 38.02, "shijiazhuang");
            jedis.geoadd("city", 118.01, 39.38, "tangshan");
            jedis.geoadd("city", 115.29, 38.51, "baoding");

            System.out.println(jedis.geopos("city", "beijing"));
            System.out.println(jedis.geopos("city", "tianjin"));
            System.out.println(jedis.geopos("city", "shijiazhuang"));
            System.out.println(jedis.geopos("city", "tangshan"));
            System.out.println(jedis.geopos("city", "baoding"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void geodist() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.geoadd("city", 116.28, 39.55, "beijing");
            jedis.geoadd("city", 117.12, 39.08, "tianjin");
            System.out.println(jedis.geodist("city", "beijing", "tianjin", GeoUnit.KM));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void georadius() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.geoadd("city", 116.28, 39.55, "beijing");
            jedis.geoadd("city", 117.12, 39.08, "tianjin");
            jedis.geoadd("city", 114.29, 38.02, "shijiazhuang");
            jedis.geoadd("city", 118.01, 39.38, "tangshan");
            jedis.geoadd("city", 115.29, 38.51, "baoding");

            GeoRadiusParam geoRadiusParam = GeoRadiusParam.geoRadiusParam();
            geoRadiusParam.withCoord().withDist().count(10).sortAscending();

            List<GeoRadiusResponse> cities = jedis.georadius("city", 110.0, 38.0, 600, GeoUnit.KM, geoRadiusParam);
            for (GeoRadiusResponse geo : cities) {
                System.out.println(geo.getMemberByString() + " distance: " + geo.getDistance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void georadiusByMember() {
        init();
        try (JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6380, 10000, "123");
             Jedis jedis = jedisPool.getResource()) {

            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("ping error...");
            }

            jedis.geoadd("city", 116.28, 39.55, "beijing");
            jedis.geoadd("city", 117.12, 39.08, "tianjin");
            jedis.geoadd("city", 114.29, 38.02, "shijiazhuang");
            jedis.geoadd("city", 118.01, 39.38, "tangshan");
            jedis.geoadd("city", 115.29, 38.51, "baoding");

            GeoRadiusParam geoRadiusParam = GeoRadiusParam.geoRadiusParam();
            geoRadiusParam.withCoord().withDist().count(10).sortDescending();

            List<GeoRadiusResponse> cities = jedis.georadiusByMember("city", "beijing", 100, GeoUnit.KM, geoRadiusParam);
            for (GeoRadiusResponse geo : cities) {
                System.out.println(geo.getMemberByString() + " distance: " + geo.getDistance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
