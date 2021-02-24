package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author JoeZhou
 */
class JedisClusterTest {

    @Test
    void jedisCluster() throws IOException {
        JedisCluster jedisCluster = null;
        try {
            Set<HostAndPort> nodes = new HashSet<>();
            nodes.add(new HostAndPort("127.0.0.1", 7011));
            nodes.add(new HostAndPort("127.0.0.1", 7012));
            nodes.add(new HostAndPort("127.0.0.1", 7013));
            nodes.add(new HostAndPort("127.0.0.1", 7014));
            nodes.add(new HostAndPort("127.0.0.1", 7015));
            nodes.add(new HostAndPort("127.0.0.1", 7016));
            jedisCluster = new JedisCluster(nodes, 10000, new JedisPoolConfig());
            jedisCluster.set("jedis-cluster-name", "jedis-cluster-value");
            System.out.println(jedisCluster.get("jedis-cluster-name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisCluster != null) {
                jedisCluster.close();
            }
        }


    }
}
