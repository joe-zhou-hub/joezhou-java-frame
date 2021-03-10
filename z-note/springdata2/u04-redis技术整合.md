# 1. springboot整合Jedis

**流程：** 引入 `spring-boot-starter-data-redis` 和 `jedis` 依赖：
- 主配：配置单机jedis，均以 `spring.redis` 为前缀：
    - `database=0`：使用redis中的数据库索引，默认使用0号。
	- `host=127.0.0.1`：单机redis-server的主机地址，默认127.0.0.1，对外开放使用0.0.0.0。
	- `port=6379`：单机redis-server的端口，默认6379
	- `timeout=3000`：连接超时时间，单位毫秒。
	- `password=1234`：redis-server的认证密码，默认为空。
- 开发 `classpath:jedis-pool.properties`：配置jedis连接池，均以 `spring.redis.jedis.pool` 为前缀：
    - `max-active=1024`：连接池最大连接数，负值表示无限制。
    - `max-idle=200`：连接池中的最大空闲连接数。
    - `min-idle=0`：连接池中的最小空闲连接数。
    - `max-wait=10000`：连接池最大阻塞等待时间，单位毫秒，负值表示无限制。
- 开发 `classpath:jedis-cluster.properties`：配置集群列表，以 `spring.redis` 为前缀：
    - `cluster.nodes=127.0.0.1:7011,127.0.0.1:7012...`
- 开发 `classpath:jedis-sentinel.properties`：配置哨兵列表，以 `spring.redis` 为前缀：
    - `sentinel.nodes=127.0.0.1:27011,127.0.0.1:27012...`
- tst: `c.j.s.jedis.JedisConfigTest`：
    - `jedisPool()`：注入 `r.c.j.JedisPool` 以获取Jedis连接并操作数据。
    - `jedisCluster()`：注入 `r.c.j.JedisCluster` 以操作集群数据。
    - `jedisSentinel()`：注入 `r.c.j.JedisSentinelPool` 以获取Jedis连接并操作数据。



