**配置：**
```properties
# REDIS (RedisProperties)
spring.redis.cluster.max-redirects= 
# Maximum number of redirects to follow when executing commands across the cluster.
spring.redis.cluster.nodes= # Comma-separated list of "host:port" pairs to bootstrap from.
spring.redis.database=0 # Database index used by the connection factory.
spring.redis.url= 
# Connection URL, will override host, port and password (user will be ignored), 
# e.g. redis://user:password@example.com:6379
spring.redis.host=localhost # Redis server host.
spring.redis.password= # Login password of the redis server.
spring.redis.ssl=false # Enable SSL support.
spring.redis.pool.max-active=8 
# Max number of connections that can be allocated by the pool at a given time. 
# Use a negative value for no limit.
spring.redis.pool.max-idle=8 
# Max number of "idle" connections in the pool. 
# Use a negative value to indicate an unlimited number of idle connections.
spring.redis.pool.max-wait=-1 
# Maximum amount of time (in milliseconds) a connection allocation should block 
# before throwing an exception when the pool is exhausted. Use a negative value to block indefinitely.
spring.redis.pool.min-idle=0 
# Target for the minimum number of idle connections to maintain in the pool. 
# This setting only has an effect if it is positive.
spring.redis.port=6379 # Redis server port.
spring.redis.sentinel.master= # Name of Redis server.
spring.redis.sentinel.nodes= # Comma-separated list of host:port pairs.
spring.redis.timeout=0 # Connection timeout in milliseconds.
```