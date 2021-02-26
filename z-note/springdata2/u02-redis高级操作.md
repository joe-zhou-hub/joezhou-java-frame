# 1. Jedis

**概念：** 官方推荐使用 [Jedis](http://xetorthio.github.io/jedis/) 作为java语言的客户端，且提供了 `r.c.j.JedisPool` 连接池以避免频繁创建和销毁jedis连接产生的性能消耗：
- 开发项目 `springdata2-redis`：添加项目依赖 `jedis`。
- 开发测试类 `c.j.s.jedis.JedisPoolTest`：
    - `new JedisPoolConfig()`：构建连接池配置实例。
    - `poolConfig.setMaxTotal()`：设置连接池最多容纳连接数，负值表示无限制。
    - `poolConfig.setMaxWaitMillis()`：设置连接池最大阻塞等待时间，负值表示无限制。
    - `poolConfig.setMaxIdle()/setMinIdle()`：设置连接池中的最大/最小空闲连接数。
    - `poolConfig.setBlockWhenExhausted()`：默认true，表示连接耗尽时是否阻塞直到超时，false则抛异常。
    - `new JedisPool()`：构建连接池对象，构造参数为连接池配置实例，IP，端口号，超时时间和密码。
    - `jedisPool.getResource()`：从连接池中获取一个Jedis实例。
    - `jedis.ping()`：返回是否连通redis服务，成功连通返回 `PONG`。
    - `jedis.set(K,V)`：调用redis的 `set` 命令，其余命令同理。

# 2. 慢查询

**概念：** 慢查询是一个存放慢命令的FIFO的定长队列，队列保存在内存中，随服务端关闭而销毁，建议持久化：
- 静态配置：在主配中进行配置，需要重启节点或在首次启动节点时生效：
    - `slowlog-log-slower-than`：配置慢查询阈值，单位微妙，超过此阈值的命令被记录，负值表示关闭慢查询。
    - `slowlog-max-len`：设置慢查询队列长度，超过此个数时，先进的记录会被丢弃。
- 动态配置：在客户端使用命令进行配置，无需重启节点：
    - `config set slowlog-log-slower-than 1000`：动态配置慢查询阈值为1000微秒，即1毫秒。
    - `config set slowlog-max-len 1000`：动态配置慢查询队列长度为1000个。
- 开发测试类 `c.j.s.jedis.SlowLogTest`：
    - `jedis.slowlogGet(3)`：获取慢查询队列中的3条数据，对应 `slowlog get 3` 命令。
    - `jedis.slowlogLen()`：获取慢查询队列中有多少条数据，对应 `slowlog len` 命令。
    - `jedis.slowlogReset()`：清空慢查询队列中的数据，对应 `slowlog reset` 命令。

# 3. PipeLine

**概念：** pipeline是流水线功能，将一批相同或不同的命令打包后，通过一次网络IO到达服务端，批处理然后将结果返回，主要目的就是为了节省网络IO，但并不能节省命令执行时间。
- 特点：对比mget操作：mget是原子重量级操作，在服务端一次性执行，且仅能执行get命令：
    - pipeline可以打包相同命令或者不同命令，打命令包的数量不要过多。
    - pipeline是非原子操作，命令包在服务端会被解包并一条一条执行，过程中可能穿插其他命令。
    - pipeline的每批命令都只能作用在一个节点上。
- 开发测试类 `c.j.s.jedis.PipelineTest`：
    - `jedis.pipelined()`：获取Pipeline实例。
    - `pipeline.hset()`：使用Pipeline调用 `hset` 命令。
    - `pipeline.syncAndReturnAll()`：异步执行并返回全部结果。

# 4. 发布订阅

**概念：** redis支持发布订阅模型，但不支持消息堆积，即订阅者无法查看历史消息：
- 发布订阅模型：频道不存在时会在服务端自动创建：
    - 订阅者subscriber订阅频道channel
    - 发布者publisher将信息发布到频道channel	
    - 订阅者subscriber就会收到该信息
- 开发订阅者监听 `c.j.s.pubsub.SubscriberListener`：建议使用构造接收订阅者名称：
    - 继承 `r.c.j.JedisPubSub` 并重写 `onMessage()/onSubscribe()/onUnsubscribe()`。
- 开发订阅者类 `c.j.s.pubsub.SubscriberA/SubscriberB/SubscriberC`：
    - `jedis.pubsubChannels("*")`：列出所有频道，对应 `pubsub channels` 命令。
    - `jedis.pubsubNumSub("sina")`：列出sina频道有多少订阅者，对应 `pubsub numsub sina` 命令。
    - `jedis.subscribe()`：订阅频道并阻塞，对应 `subscribe sina` 命令，参数为订阅者监听实例和频道列表。
    - `jedisPubSub.onUnsubscribe()`：退订频道，对应 `unsubscribe sina` 命令。
- 开发发布者类 `c.j.s.pubsub.Publisher`： 
    - `jedis.publish()`：向指定频道发送消息，对应 `publish sina hi` 命令，参数为频道和消息。

# 5. BitMap

**概念：** 字符串底层使用位图，即二进制形式存储，而bitmap可以直接对位图进行按位操作：
- 自动拓展：bitmap可以以字节（8bit）为单位自动扩容，如：
    - `a(ascii=98)` 的位图为 `01100001`。
    - `aa(ascii=98 98)` 的位图为 `01100001 01100001`。
- 常用场景：记录网站登录用户的id，即在用户id对应的索引上设置1或0以表示登录或未登录。
- 开发测试类 `c.j.s.jedis.BitMapTest`：key不存在的时候会自动创建：
    - `jedis.getbit("name", 0)`：返回name位图0号位上的布尔值，对应 `getbit name 0` 命令。
    - `jedis.setbit("name", 0, true);`：设置name位图0号位上的值为1，对应 `setbit name 0 1` 命令：
        - a的位图仅8位，若强行操作1000号位，则会将其9-999位全补0，严重耗时。
    - `jedis.bitcount("name", 0, 9)`：返回name位图0-9号位中有多少个1，对应 `bitcount name 0 9` 命令。
    - `jedis.bitop(BitOP.AND/OR/NOT/XOR, "k3", "k2", "k1")`：将k1和k2位图的交/并/差/亦或结果存入k3，对应 `bitop and/or/not/xor k3 k1 k2` 命令。
    - `jedis.bitpos("name", false)`：返回name的位图中，第一个0的位置，对应 `bitpos name 1` 命令。
- 对比set：假设网站有1亿注册用户，则使用bitmap最多需要存储1亿个用户id作为索引，共需 `1bit*1亿=12.5M` 内存：
    - 若日平均活跃用户5000W，用set集合存储int类型id，共需 `32bit*5000W=200M` 内存，差于bitmap。
    - 若日平均活跃用户10W，用set集合存储int类型id，共需 `32bit*10W=4M` 内存，优于bitmap。
- 布隆过滤器：用于解决缓存穿透，可过滤大量无效请求，底层是一张bitmap位图和N个hash函数，函数越多失误率越低：
    - 对数据库中的每个元素依次执行布隆过滤器的N个函数，然后在结果对应的bitmap位图索引处标1。
    - 当客户端请求查询某元素时，先经过布隆过滤器，分别执行N个函数，然后在bitmap中比对，全为1才允许通过。

# 6. GEO

**概念：** geo表示地理信息定位，存储经纬度，计算两地计算，范围计算等，是redis3.2+添加的一个特性，底层使用zset结构：
- 开发测试类 `c.j.s.jedis.GeoTest`：
    - `jedis.geoadd("city", 1.1, 2.2, "beijing")`：将北京的经纬度添加geo中，对应 `geoadd city 1.1 2.2 beijing` 命令。
    - `jedis.geopos("city", "beijing")`：获取北京的经纬度，对应 `geopos city beijing` 命令。
    - `jedis.geodist("city", "bj", "tj", GeoUnit.KM)`：获取city中北京和天津的千米距离，对应 `geodist city bj tj km` 命令。
- 经纬度范围扫描：`jedis.georadius("city", 1.0, 2.0, 5, GeoUnit.KM, geoRadiusParam)`：扫描city中以经纬度 `[1.0, 2.0]` 为中心，5km范围内其他元素集合，对应 `georadius city 1.0 2.0 5 km` 命令：
    - `geoRadiusParam` 参数可选，用于配置额外参数，通过 `GeoRadiusParam.geoRadiusParam()` 获取。
    - `geoRadiusParam.withCoord()`：设置返回结果中包含经纬度，对应 `... withcoord` 命令。
    - `geoRadiusParam.withDist()`：设置返回结果中包含距离中心节点的位置，对应 `... withdist` 命令。
    - `geoRadiusParam.count(10)`：设置最多返回10条元素，对应 `... count 10` 命令。
    - `geoRadiusParam.sortAscending()`：设置返回结果升序排列，对应 `... asc 10` 命令。
    - 经纬度可以改为geo中存在的元素名如 `beijing`，此时将以 `beijing` 为中心扫描。
- 元素范围扫描：`jedis.georadiusByMember("city", "beijing", 5, GeoUnit.KM, geoRadiusParam)`：扫描city中以北京为中心，5km范围内其他元素集合，对应 `georadiusbymember city 1.0 2.0 5 km` 命令。

# 7. Redis事务

**概念：** redis事务的本质是一次性顺序执行多条命令，批量命令先被被放入队列缓存，当收到 `exec` 命令时开始依次顺序执行，任意命令执行失败都不会影响其他命令，即不保证原子性也没有回滚操作，且整个过程也不会被其他客户端打断：
- 提交事务：
    - `multi`：开启事务。
    - `set a 1`：命令进入缓存队列，返回QUEUED。
    - `set b 2`：命令进入缓存队列，返回QUEUED。
    - `exec`：执行事务，两条命令依次执行，返回两个OK。
- 取消事务：
    - `multi`：开启事务。
    - `set a 1`：命令进入缓存队列，返回QUEUED。
    - `set b 2`：命令进入缓存队列，返回QUEUED。
    - `discard`：取消事务，返回OK。
    - `get a`：数据未被改动，返回nil。
- 运行异常：
    - `multi`：开启事务。
    - `set a "a"`：命令进入缓存队列，返回QUEUED。
    - `incr a`：命令仍进入缓存队列，返回QUEUED。
    - `exec`：执行事务，命令1成功，命令2对字符串自增导致报错。
- watch跟踪：使用两个客户端测试watch效果：
    - 客户端A：`set a 1`：设置a值为1，返回OK。
    - 客户端A：`watch a`：跟踪a，初始值为1，返回OK。
    - 客户端A：`multi`：开启事务。
    - 客户端B：`get a`：返回a值为1。
    - 客户端B：`set a 2`：将a值修改为2，返回OK。
    - 客户端A：`set a 3`：将a值修改为3，命令进入缓存队列，返回QUEUED。
    - 客户端A：`exec`：执行事务，但因为a值被客户端B改动，事务失败返回nil。

# 8. 持久化

**概念：** 持久化指的是将内存中的数据备份到硬盘的操作，主要用于避免宕机丢失数据的情况，redis可以同时使用两种持久化方式，但此时redis重启时会优先读取AOP文件来恢复数据。

## 8.1 RDB持久化

**概念：** RDB是半持久化，即将内存数据创建快照并备份到磁盘的RDB文件中，重读RDB文件可以恢复数据，RDB文件内部采用二进制压缩，体积比较小，数据恢复速度快，但系统一旦在某次持久化操作之前宕机，则有可能丢失一部分数据：
- 同步触发：客户端执行 `save` 命令时生成临时RDB文件并向其dump数据，最后替换旧RDB文件，过程不会额外消耗内存但会阻塞其他命令，属于重量级命令。
- 异步触发：客户端执行 `bgsave` 命令时主进程fork出一个子进程来异步执行 `save` 命令，该命令不阻塞，立刻返回 `background saving start` 消息，但需要额外消耗内存进行fork操作。
- 自动触发：
    - 默认配置下，redis在60s内进行1W次写，300s内进行10次写，或900s内进行1次写时触发 `bgsave`。
    - `debug reload` 操作，`shutdown` 操作和主从全量复制时时也会生成RDB文件。
- RDB配置：
    - `dbfilename dump-6380.rdb`：配置RDB文件。
    - `stop-writes-on-bgsave-error yes`：配置当 `bgsave` 出错时停止操作。
    - `rdbcompression yes`：配置对RDB文件进行压缩。
    - `rdbchecksum yes`：对RDB文件进行检查。
    - `redis 60 10000`：在60s内做了1W次写操作就触发 `bgsave`，建议关闭。
    - `redis 300 10`：在300s内做了10次写操作就触发 `bgsave`，建议关闭。
    - `redis 900 1`：在900s内做了1次写操作就触发 `bgsave`，建议关闭。   

## 8.2 AOF持久化

**概念：** AOF是全持久化，将每一次的写命令都追加到磁盘的aof_buffer缓冲区，然后再fsync到硬盘的AOF文件中，此过程简称刷盘，是轻量级命令，重新执行AOF文件中的命令可以恢复数据，且数据完整性和安全性更高，但相对的日志信息过大导致数据恢复速度慢：
- AOF刷盘策略：
    - `always`：redis每次写操作都立刻刷盘，数据完整度高，但是磁盘IO次数多，效率低。
    - `everysec`：redis每秒刷盘，磁盘IO次数少，但有可能会丢失最后1秒的数据，是redis默认配置。
    - `no`：由OS决定如何刷盘，不用管，不可控，不建议。
- AOF配置：
    - `appendonly yes`：开启AOF持久化，默认是关闭的。
    - `appendfilename appendonly-6380.aof`：配置AOF文件名。
    - `appendfsync everysec`：配置刷盘策略。
    - `no-appendfsync-on-rewrite yes`：允许在AOF重写时继续做append操作。
    - `auto-aof-rewrite-min-size 64mb`：当AOF文件超过64mb时，AOF自动重写条件之一。
    - `auto-aof-rewrite-percentage 100`：当AOF文件增长率超过100%时，AOF自动重写条件之一。
- AOF自动重写：AOF当前文件大小超过 `auto-aof-rewrite-min-size` 值，且增长率（ `AOF文件当前大小 - 上一次AOF文件大小)/上一次AOF文件大小` ）超过 `auto-aof-rewrite-percentage` 值时候自动触发AOP重写，即将一些被覆盖的，过期的命令进行优化，以减少文件大小，加速恢复速度。
- AOF手动重写：客户端发送 `bgrewriteaof` 命令会立刻执行AOF重写：
    - redis主进程fork一个子进程去异步执行重写命令。
    - 此时redis客户端发送的任何写命令都会被暂时追加到 `aof_rewrite_buf` 缓冲区中。
    - 子进程执行AOF重写操作完成后，得到一个新的AOF文件。
    - 将 `aof_rewrite_buf` 缓冲区中的命令追加到新的AOF文件中。
    - 使用新的AOF文件替换掉旧的AOF文件。

## 8.3 无持久化

**概念：** 如果不想使用持久化，则需要将将RDB配置全部注释掉，设置 `appendonly no`，并执行 `save ""` 命令
把持久化的本地文件全部干掉。