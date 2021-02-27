package com.joezhou.springdata2redis.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author JoeZhou
 */
class JedisClusterTest {

    private Set<HostAndPort> nodes;

    void init() {
        nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 7011));
        nodes.add(new HostAndPort("127.0.0.1", 7012));
        nodes.add(new HostAndPort("127.0.0.1", 7013));
        nodes.add(new HostAndPort("127.0.0.1", 7014));
        nodes.add(new HostAndPort("127.0.0.1", 7015));
        nodes.add(new HostAndPort("127.0.0.1", 7016));
    }

    @Test
    void jedisCluster() {
        init();
        try (JedisCluster jedisCluster = new JedisCluster(nodes, 1000, new JedisPoolConfig())) {
            jedisCluster.set("jedis-cluster-name", "jedis-cluster-value");
            System.out.println(jedisCluster.get("jedis-cluster-name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void operateOnAllNodes() {
        init();
        try (JedisCluster jedisCluster = new JedisCluster(nodes, 10000, new JedisPoolConfig())) {
            Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
            for (Map.Entry<String, JedisPool> entry : clusterNodes.entrySet()) {
                Jedis jedis = entry.getValue().getResource();

                // operate only on the master node
                if (jedis.info("replication").contains("role:master")) {
                    System.out.println(jedis.keys("*"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void failover() {
        init();
        for (int i = 0; true; i++) {
            try (JedisCluster jedisCluster = new JedisCluster(nodes, 1000, new JedisPoolConfig())) {
                jedisCluster.setex("k" + i, 5, "v" + i);
                System.out.println(jedisCluster.get("k" + i));
                TimeUnit.SECONDS.sleep(1L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
