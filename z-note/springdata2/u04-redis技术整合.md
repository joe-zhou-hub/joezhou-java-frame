# 1. springboot整合Jedis

**流程：** 引入 `spring-boot-starter-data-redis` 和 `jedis` 依赖：
- 开发 `classpath:jedis.properties`：配置jedis连接池和redis通用配置：
    - `pool.max-active=1024`：连接池最大连接数，默认8，-1表示无限制。
    - `pool.max-idle=200`：连接池最大空闲连接数，默认8，-1表示无限制。
    - `pool.min-idle=0`：连接池最小空闲连接数，默认0，-1表示无限制。
    - `pool.max-wait=10000`：连接池最大阻塞等待时间，单位毫秒，默认-1表示永不超时。
    - `redis.timeout=3000`：连接超时时间，单位毫秒。
	- `redis.host=127.0.0.1`：单机redis-server的主机地址，默认127.0.0.1。
	- `redis.port=6380`：单机redis-server的端口，默认6379。
	- `redis.password=1234`：单机redis-server的认证密码，默认为空字符串。
    - `sentinel.nodes=127.0.0.1:27001,127.0.0.1:27002...`：哨兵地址列表。
    - `sentinel.master=my-master`：哨兵监控的主从结构名。
    - `cluster.nodes=127.0.0.1:7011,127.0.0.1:7012...`：集群地址列表。

## 1.1 整合单机redis

**流程：**
- 开发配置类 `c.j.s.config.JedisConfig`：
    - 使用 `@PropertySource()` 引入 `jedis.properties` 文件。
    - 使用 `@Value` 将 `jedis.properties` 文件中的值注入类属性变量。
    - 配置 `r.c.j.JedisPool` 对应的bean。 
- tst: `c.j.s.jedis.JedisConfigTest.jedisPool()`：
    - 注入 `r.c.j.JedisPool`，获取Jedis连接并操作数据。

## 1.2 整合哨兵redis

**流程：**
- 开发配置类 `c.j.s.config.JedisSentinelConfig`：
    - 使用 `@PropertySource()` 引入 `jedis.properties` 文件。
    - 使用 `@Value` 将 `jedis.properties` 文件中的值注入类属性变量。
    - 配置 `r.c.j.JedisSentinelPool` 对应的bean。
- tst: `c.j.s.jedis.JedisConfigTest.jedisSentinel()`：
    - 注入 `r.c.j.JedisSentinelPool`，获取Jedis连接并操作数据。

## 1.3 整合集群redis

**流程：**
- 开发配置类 `c.j.s.config.JedisClusterConfig`：
    - 使用 `@PropertySource()` 引入 `jedis.properties` 文件。
    - 使用 `@Value` 将 `jedis.properties` 文件中的值注入类属性变量。
    - 配置 `r.c.j.JedisCluster` 对应的bean。
- tst: `c.j.s.jedis.JedisConfigTest.jedisCluster()`：
    - 注入 `r.c.j.JedisCluster`，直接操作集群数据。  

# 2. springboot整合StringRedisTemplate

**概念：** `StringRedisTemplate` 高度封装Jedis的API且可以自动管理连接池，但效率要低于原生Jedis：
- 引入 `spring-boot-starter-data-redis` 依赖。
- 在主配中配置redis：均以 `spring.redis` 为前缀：    
    - `spring.redis.timeout=3000`：连接超时时间，单位毫秒。
	- `spring.redis.host=127.0.0.1`：单机redis-server的主机地址，默认127.0.0.1。
	- `spring.redis.port=6380`：单机redis-server的端口，默认6379。
	- `spring.redis.password=1234`：单机redis-server的认证密码，默认为空字符串。   
    - `spring.redis.lettuce.pool.max-active=1024`：连接池最大连接数，默认8，-1表示无限制。
    - `spring.redis.lettuce.pool.max-idle=200`：连接池最大空闲连接数，默认8，-1表示无限制。
    - `spring.redis.lettuce.pool.min-idle=0`：连接池最小空闲连接数，默认0，-1表示无限制。
    - `spring.redis.lettuce.pool.max-wait=10000`：连接池最大阻塞等待时间，单位毫秒，默认-1表示永不超时。
- tst: `c.j.s.template.StringRedisTemplateTest`：
    - 注入 `o.s.d.r.c.StringRedisTemplate`，直接操作集群数据。 
    - `stringRedisTemplate.opsForValue()` 对象用于操作字符串。
    - `stringRedisTemplate.opsForHash()` 对象用于操作hash。
    - `stringRedisTemplate.opsForList()` 对象用于操作list。
    - `stringRedisTemplate.opsForSet()` 对象用于操作set。
    - `stringRedisTemplate.opsForZSet()` 对象用于操作sorted set。


