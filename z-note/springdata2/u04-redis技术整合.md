# 1. springboot整合Jedis

**流程：** 引入 `spring-boot-starter-data-redis` 和 `jedis` 依赖：
- 开发 `classpath:jedis-pool.properties`：配置jedis连接池，均以 `spring.redis.jedis.pool` 为前缀：
    - `max-active=1024`：连接池最大连接数，负值表示无限制。
    - `max-idle=200`：连接池中的最大空闲连接数。
    - `min-idle=0`：连接池中的最小空闲连接数。
    - `max-wait=10000`：连接池最大阻塞等待时间，单位毫秒，负值表示无限制。

## 1.1 整合单机redis

**流程：**
- 在主配中配置单机jedis，均以 `spring.redis` 为前缀：
    - `database=0`：使用redis中的数据库索引，默认使用0号。
	- `host=127.0.0.1`：单机redis-server的主机地址，默认127.0.0.1，对外开放使用0.0.0.0。
	- `port=6379`：单机redis-server的端口，默认6379
	- `timeout=3000`：连接超时时间，单位毫秒。
	- `password=1234`：redis-server的认证密码，默认为空。
- 开发 `c.j.s.config.JedisConfig`：
    - 标记 `@Configuration` 使其成为配置类。
    - 使用 `@PropertySource()` 引入Jedis连接池属性文件。
    - 使用 `@Value` 将属性文件中的值注入类属性变量。
    - 配置 `r.c.j.JedisPool` 对应的bean。 
- tst: `c.j.s.jedis.JedisConfigTest.jedisPool()`：
    - 注入 `r.c.j.JedisPool`，获取Jedis连接并操作数据。

## 1.2 整合哨兵redis

**流程：**
- 开发 `classpath:jedis-sentinel.properties`：均以 `spring.redis` 为前缀：
    - `sentinel.nodes=127.0.0.1:27001,127.0.0.1:27002...`：哨兵地址列表。
    - `sentinel.master=my-master`：哨兵监控的主从结构名。
    - `timeout=3000`：连接超时时间，单位毫秒。
- 开发配置类 `c.j.s.config.JedisSentinelConfig`：
    - 使用 `@PropertySource()` 引入哨兵属性文件及Jedis连接池属性文件。
    - 使用 `@Value` 将属性文件中的值注入对应属性变量。
    - 配置 `r.c.j.JedisSentinelPool` 对应的bean。
- tst: `c.j.s.jedis.JedisConfigTest.jedisSentinel()`：
    - 注入 `r.c.j.JedisSentinelPool`，获取Jedis连接并操作数据。

## 1.3 整合集群redis

**流程：**
- 开发 `classpath:jedis-cluster.properties`：均以 `spring.redis` 为前缀：
    - `cluster.nodes=127.0.0.1:7011,127.0.0.1:7012...`：集群地址列表。
    - `timeout=3000`：连接超时时间，单位毫秒。
- 开发配置类 `c.j.s.config.JedisClusterConfig`：
    - 使用 `@PropertySource()` 引入集群属性文件及Jedis连接池属性文件。
    - 使用 `@Value` 将属性文件中的值注入对应属性变量。
    - 配置 `r.c.j.JedisCluster` 对应的bean。
- tst: `c.j.s.jedis.JedisConfigTest.jedisCluster()`：
    - 注入 `r.c.j.JedisCluster`，直接操作集群数据。  

# 2. springboot整合StringRedisTemplate

**概念：** `StringRedisTemplate` 是Jedis的高度封装，相比Jedis而言，可以更方便地更换redis的java客户端，且多了自动管理连接池的特性，但是它的效率要低于Jedis：
- 引入 `spring-boot-starter-data-redis` 依赖。
- 在主配中配置redis：均以 `spring.redis` 为前缀：
    - `database=0`：使用redis中的数据库索引，默认使用0号。
    - `host=127.0.0.1`：单机redis-server的主机地址，默认127.0.0.1，对外开放使用0.0.0.0。
    - `port=6379`：单机redis-server的端口，默认6379
    - `timeout=3000`：连接超时时间，单位毫秒。
    - `lettuce.pool.max-active=1024`：连接池最大连接数，负值表示无限制。
    - `lettuce.pool.max-idle=200`：连接池中的最大空闲连接数，默认值是8，负值表示无限制。
    - `lettuce.pool.min-idle=0`：连接池中的最小空闲连接数，默认值是0，负值表示无限制。
    - `lettuce.pool.max-wait=10000`：连接池最大阻塞等待时间，单位毫秒，负值表示永不超时。
- tst: `c.j.s.template.StringRedisTemplateTest`：
    - 注入 `o.s.d.r.c.StringRedisTemplate`，直接操作集群数据。 
    - `stringRedisTemplate.opsForValue()` 对象用于操作字符串。
    - `stringRedisTemplate.opsForHash()` 对象用于操作hash。
    - `stringRedisTemplate.opsForList()` 对象用于操作list。
    - `stringRedisTemplate.opsForSet()` 对象用于操作set。
    - `stringRedisTemplate.opsForZSet()` 对象用于操作sorted set。


