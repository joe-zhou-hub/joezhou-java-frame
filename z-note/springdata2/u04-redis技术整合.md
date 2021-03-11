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

StringRedisTemplate是SpringDataRedis中对JedisApi的高度封装，SpringDataRedis相对于Jedis来说可以方便地更换Redis的Java客户端，比Jedis多了自动管理连接池的特性，方便与其他Spring框架进行搭配使用，但是它的效率要低于Jedis。

使用springboot-starter整合reids实战

官网：https://docs.spring.io/spring-boot/docs/2.1.0.BUILD-SNAPSHOT/reference/htmlsingle/#boot-features-redis
集群文档：https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#cluster

springboot整合redis相关依赖引入
<!--spring-boot-starter-data-redis-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

	    
在application.properties文件中进行redis相关配置
spring.redis.cluster.nodes=192.168.174.221:6381, 192.168.174.221:6382, 192.168.174.221:6383, 192.168.174.221:6384, 192.168.174.221:6385, 192.168.174.221:6386

redis:
  database: 0 # 使用redis中的数据库索引，默认使用0号
  host: 127.0.0.1 # Redis服务器地址，默认127.0.0.1
  port: 6379 # Redis服务器连接端口，默认6379
  timeout: 3000 # 连接超时时间 单位 ms（毫秒）
  password: joe # 认证密码，默认为空
  lettuce:
    pool:
      max-idle: 200 # 连接池中的最大空闲连接，默认值是8，设为-1表示无限制
      min-idle: 200 # 连接池中的最小空闲连接，默认值是0，设为-1表示无限制
      max-active: 2000 # 连接池中的最大活跃连接数，即如果连接池已经分配了maxActive个jedis实例，则连接池会exhausted(耗尽)，设为-1表示无限制
      max-wait: 1000 # 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时


RedisController.java测试
/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    private StringRedisTemplate redis;

    @Autowired
    public RedisController(StringRedisTemplate redis) {
        this.redis = redis;
    }

    @RequestMapping("createAccount.action")
    public String createAccount() {

        // 向redis里存入username和设置缓存时间(5分钟)
        // 向redis里存入password
        redis.opsForValue().set("username", "admin", 60 * 5, TimeUnit.SECONDS);
        redis.opsForValue().set("password", "123");
        Boolean hasId = redis.hasKey("id");
        if (hasId != null && hasId) {
            redis.boundValueOps("id").increment(1);
            redis.opsForValue().set("id", redis.opsForValue().get("id"));
        } else {
            redis.opsForValue().set("id", "0");
        }

        return "create done";
    }

    @RequestMapping("retrieveAccount.action")
    public Map<String, Object> retrieveAccount() {
        Map<String, Object> map = new HashMap<>(3);
    
        // 获取username
        String username = redis.opsForValue().get("username");
    
        // 获取username的过期时间
        Long usernameExpire = redis.getExpire("username");
    
        // 获取password
        String password = redis.opsForValue().get("password");
    
        // 获取password的过期时间并换算成指定单位
        Long passwordExpire = redis.getExpire("password", TimeUnit.MINUTES);
    
        // 获取id
        String id = redis.opsForValue().get("id");
    
        // 获取id的过期时间并换算成指定单位
        Long idExpire = redis.getExpire("id", TimeUnit.MINUTES);
    
        map.put("编号", id);
        map.put("编号过期时间", idExpire);
        map.put("账号", username);
        map.put("账号过期时间", usernameExpire);
        map.put("密码", password);
        map.put("密码过期时间", passwordExpire);
        
        return map;
    }

    @RequestMapping("deleteAccount.action")
    public String deleteAccount() {

        Boolean hasId = redis.hasKey("id");
        Boolean hasUsername = redis.hasKey("username");
        Boolean hasPassword = redis.hasKey("password");
        if (hasId != null && hasId) {
            redis.delete("id");
        }
        if (hasUsername != null && hasUsername) {
            redis.delete("username");
        }
        if (hasPassword != null && hasPassword) {
            redis.delete("password");
        }
        return "delete done";
    }

    @RequestMapping("createSet.action")
    public String createSet() {

        // 向ageSet中存放set集合
        redis.opsForSet().add("ageSet", "1", "2", "3");

        // 设置ageSet过期时间
        redis.expire("ageSet", 1000, TimeUnit.MILLISECONDS);

        // 根据ageSet查看集合中是否存在指定数据
        redis.opsForSet().isMember("ageSet", "1");

        // 获取ageSet集合中的所有元素
        redis.opsForSet().members("ageSet");
        return "done";
    }

}


stringRedisTemplate.opsForValue();//操作字符串
stringRedisTemplate.opsForHash();//操作hash
stringRedisTemplate.opsForList();//操作list
stringRedisTemplate.opsForSet();//操作set
stringRedisTemplate.opsForZSet();//操作有序set


