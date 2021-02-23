# 1. 概念入门

**概念：** 
- NoSQL：即Not-Only-SQL，一种泛指非关系型数据库的全新理念，如键值对结构的redis，文档结构的mongodb，图形结构的neo4j等：
    - 高扩展：NoSQL数据之间无关联，故非常容易进行拓展。
    - 高性能：NoSQL数据结构简单，在海量数据量场景下具有高读写性能。
    - 更灵活：NoSQL无需事前建立表结构，更灵活的操作数据，避免繁琐的表，字段的关系操作。
    - 高可用：NoSQL在不太影响性能的情况下，可以方便的实现高可用的架构，如集群等。
- redis：基于C语言开发的一款遵守BSD协议的高性能开源数据库，github，twitter，stackOverFlow，阿里巴巴，百度，美团，搜狐，新浪微博等都在使用：
    - 高性能：官方测试50个并发执行10W个请求时，redis读速为11W次/s，写速为8.1W次/s。
    - 单线程：一次仅运行一条命令，更简单且无并发问题，避免了多线程切换开销和竞态消耗。
    - 持久化：默认所有数据保存在内存中以提高性能，亦可将数据更新异步地保存到硬盘。
    - 多结构：支持字符串，哈希，列表，集合等五种数据结构以更灵活的操作数据。
    - 多场景：用于缓存数据为DB减压，用于高效计数如播放量和转发数等，用于消息队列系统，排行榜，社交网络，实时系统如秒杀，限购等。
- 下载安装：官网提供 [redis for linux]((http://redis.io/download))，github提供 [redis for windows](https://github.com/MSOpenTech/redis/tags)：
    - 偶数版本号如3.0为稳定版，奇数版本号如3.1为不稳定版。
    - redis2.8+支持redis-sentinel哨兵，redis3.0+支持redis-cluster集群。
- 目录结构：windows版本的redis下载后直接解压缩即可：
    - `redis-server.exe`：服务端程序，提供redis服务。
    - `redis-cli.exe`: 客户端程序，通过它连接redis服务和操作数据。
    - `redis-check-dump.exe`：本地数据库检查工具，用于对rdb文件进行检查。
    - `redis-check-aof.exe`：更新日志检查工具，用于对aof文件进行修复。
    - `redis-benchmark.exe`：性能测试工具，用于模拟客户端向服务端发送操作请求。
    - `redis.windows.conf`: redis主配文件，在将redis作为第三方软件使用时生效。
    - `redis.windows-service.conf`：redis主配文件，在将redis作为系统服务使用时生效。
- 启动服务端：未配置环境变量时需进入redis根目录操作，且需要保持窗口：
    - cmd: `redis-server -p 6379`：以指定端口启动redis服务端，默认6379。
- 查看服务端：可以根据服务名或者服务PID查看redis服务是否启动：
    - cmd: `tasklist /fi "imagename eq redis*`：查看redis服务是否已经启动。
    - cmd: `netstat -ano | findstr 6379`：查看6379端口被哪个PID占用，如4988。
    - cmd: `tasklist /fi "pid eq 4988`：查看4988进程的详细信息。
- 启动客户端：在单独cmd窗口中启动redis客户端并保持窗口：
    - `redis-cli -h 127.0.0.1 -p 6379`：默认IP为localhost，默认端口6379。
- 部署服务端：将redis部署为windows服务可以一直保持后台运行：
    - cmd: `redis-server --service-install --service-name redis32`：部署redis32服务。
    - cmd: `redis-server --service-uninstall --service-name redis32`：卸载redis32服务。
    - cmd: `redis-server --service-start --service-name redis32`：启动redis32服务。
    - cmd: `redis-server --service-stop --service-name redis32`：停止redis32服务。
    - cmd: `net start redis32`：在任意位置启动redis32服务。
    - cmd: `net stop redis32`：在任意位置停止redis32服务。

# 2. 主配文件

**概念：** redis主配文件可自主拓展开发，如 `start.conf`：
- 常用配置：
    - `daemonize`：改为yes表示以守护进程启动redis，注意windows无效。
    - `bind`：配置访问IP，需允许外网所有IP访问时必须删除此项并添加 `protected-mode no`。
    - `port`：指定端口号。
    - `dir workspace`：指定redis工作目录以存放日志文件等，需提前创建该目录。
    - `logfile 6380.log`：指定日志文件名，建议以端口号进行命名。 
    - `requirepass 123`：设置客户端登录密码为123，可提高安全性。
- 启动服务端：进入redis根目录并以指定配置启动redis服务：
    - cmd: `redis-server start.conf`。
- 启动客户端：以指定端口和指定密码连接服务端：
    - `redis-cli -p 6380 -a 123`：不填写密码也可以登录，但无操作数据权限。
- 部署服务端：以指定配置的方式将服务部署为windows服务：
    - cmd: `redis-server --service-install start.conf --service-name redis-start`。

# 3. 数据类型

**概念：** redis所有的key都是字符串类型，而value则可使用 `string/hash/list/set/zset` 五种数据类型，除string外的容器型数据结构必须遵守以下规则：
- `create if not exists`：当容器不存在时，创建容器，再进行操作。
- `drop if not elements`：当容器中无元素时，立即删除容器，释放内存。

## 3.1 通用命令

**概念：** `keys` 是重量级命令，不在生产环境中对主节点使用，其余都是轻量级命令：
- `keys *`：返回所有键。
- `keys a*`：返回以 `a` 开头的所有键。
- `keys a[c-d]*`：返回以 `a` 开头，第2位是 `c-d` 范围内字母（包括两端）的所有键。
- `keys a?c`：返回以 `a` 开头，第3位是 `c` 的所有键。
- `dbsize`：返回总键数。
- `exists a`：判断键 `a` 是否存在，存在返回1，不存在返回0。
- `del a b`：同时删除键 `a` 和 `b`，返回成功删除总数，删除失败的键计为0。
- `expire a 20`：设置键 `a` 20秒后过期，成功返回1。
- `ttl a`：返回键 `a` 的当前寿命，单位秒，键不存在返回-2，键永生返回-1。
- `persist a`：移除键 `a` 的过期时间，使其永生。
- `type a`：返回键 `a` 的数据类型，键不存在返回none。

## 3.2 字符串string

**概念：** string类型包括字符串，整数（自动转型，存值范围与long类型相同），二进制（如图片或序列化对象）等，常用于缓存，计数器，分布式锁等场景：
- 存储特性：一个string类型的键最多能存512MB的值：
    - 当值小于1MB时，扩容都是加倍现有空间。
    - 当值大于1MB时，每次扩容1MB，直到512MB。
- 常用命令：`nil` 表示无值，任何变量在没有被赋值之前的值都为 `nil`：
    - `set a 1`：永久存储 `a=1`，同名覆盖，无论键是否存在都成功，总是返回 `OK`。
    - `setnx a 1`：永久存储 `a=1`，同名覆盖，键不存在时才成功并返回1，否则返回0。
    - `set a 1 xx`：永久存储 `a=1`，同名覆盖，键存在时才成功并返回 `OK`，否则返回 `nil`。
    - `mset a 1 b 2`：批量永久存储 `a=1` 和 `b=2`，原子操作节省网络IO次数，但为重量级命令。
    - `get a`：获取 `a` 值，键不为string报错，键不存在返回 `nil`。
    - `mget a b`：批量获取 `a` 和 `b` 值，原子操作节省网络IO次数，但为重量级命令。
    - `del a`：删除 `a`，删除成功返回1，键不存在返回0。
    - `incr a`：自增 `a` 值，返回自增后结果，键不存在设置 `a=1` 并返回1。
    - `decr a`：自减 `a` 值，返回自减后结果，键不存在设置 `a=-1` 并返回-1。
    - `incrby a 5`：将 `a` 值自增5并返回，键不存在设置 `a=5` 并返回5。
    - `decrby a 5`：将 `a` 值自减5并返回，键不存在设置 `a=-5` 并返回-5。
    - `incrbyfloat a 1.5`：将 `a` 自增1.5并返回字符串1.5，负数表示自减。
    - `setex a 8 5`：存储 `a=5`，并设置8秒后过期，返回 `OK`。
    - `getset a 5`：将 `a` 值修改为 `5` 并返回原值，原子组合操作。
    - `append a 5`：将 `5` 追加到 `a` 值末尾，返回字节长度。
    - `strlen a`：返回 `a` 值长度，UTF8编码下每个中文2字节。
    - `getrange a 0 9`：返回 `a` 值中，索引0到索引9范围内的所有值。
    - `setrange a 0 9`：设置 `a` 值中，索引0的值为9。

## 3.3 哈希hash

**概念：** hash类型可视为拥有字符串属性的HashMap容器，适合于存储bean对象，如User等：
- 存储特性：若hash中包含很少的字段，那么该类型的数据也将仅占用很少的磁盘空间：
    - 每一个hash可以存储4294967295个键值对。
    - hash冲突时采用渐进式rehash操作：同时保留新旧两个hash，查询时同时查询两个hash结构，在后续的定时任务及hash操作的过程中完成从旧hash到新hash的数据迁移。
- 常用命令：
    - `hset user a 1`：为 `user` 设置 `a=1` 属性，成功返回1。
    - `hsetnx user a 1`：为 `user` 设置 `a=1` 属性，若 `a` 已存在则失败。
    - `hincrby user a 5`：为 `user` 中的 `a` 值自增5。
    - `hincrbyfloat user a 1.5`: 为 `user` 中的 `a` 值自增1.5。
    - `hget user a`：返回 `user` 中的 `a` 值，键不为string报错，键不存在返回 `nil`。
    - `hgetall user`：返回 `user` 中所有的键值对，执行速度较慢。
    - `hdel user a b`：同时删除 `user` 中的 `a` 和 `b` 属性，返回总影响数。
    - `hexists user a`：判断 `user` 中是否存在 `a` 属性，存在返回1，不存在返回2。
    - `hlen user`：返回 `user` 中所有的键值对数。
    - `hmget user a b`：批量返回 `user` 中 `a` 和 `b` 的值，原子操作节省网络IO次数，但为重量级命令。
    - `hmset user a 1 b 2`：批量设置 `user`中的 `a=1` 和 `b=2`，原子操作节省网络IO次数，但为重量级命令。
    - `hkeys user`：返回user中所有属性。
    - `hvals user`：返回user中所有属性对应的值。

## 3.4 字符列表list

**概念：** list类型是按照插入顺序排序的字符串链表，和数据结构中的LinkedList一样，我们可以在其头部 `left` 和尾部`right` 添加新元素：
- 存储特性：
    - 在插入时，如果该键并不存在，Redis将为该键创建一个新的链表。
    - 列表中元素允许重复，允许双端操作，有序
    - 如果链表中所有的元素都被移除，那么该键也将会被从数据库中删除。
    - List中可以包含的最大元素数量是4294967295。
- 常用命令：`age` 列表不存在时自动创建：
    - `lpush age 1 2`：在 `age` 头部依次插入1和2，返回插入后 `age` 的长度。
    - `rpush age 1 2`：在 `age` 尾部依次追加1和2，返回插入后 `age` 的长度。
    - `lrange age 0 2`：取出 `age` 中，索引0到索引2的全部元素，两端包括。
    - `lrange age 0 -5`：取出 `age` 中，索引0到倒数第五个元素范围内的全部元素，两端包括。
    - `lrange age 0 -1`：取出 `age` 中的全部元素。
    - `lpop age`：返回并删除（弹出） `age` 的链表头。
    - `rpop age`：返回并删除（弹出） `age` 的链表尾。
    - `linsert age before a b`：在 `age` 中的 `a` 前插入 `b`，重量级命令。
    - `linsert age after a b`：在 `age` 中的 `a` 后插入 `b`，重量级命令。
    - `lrem age 0 1`：删除 `age` 中所有的1，重量级命令。
    - `lrem age 5 1`：从左到右，删除最多5个 `age` 中1，重量级命令。
    - `lrem age -5 1`：从右到左，删除最多5个 `age` 中1，重量级命令。
    - `ltrim age 0 9`：在 `age` 中，从索引0保留到索引9，其余删除，重量级命令。
    - `lindex age 3`：返回 `age` 中索引3上的元素，重量级命令。
    - `llen age`：返回 `age` 长度。
    - `lset age 3 9`：将 `age` 中索引3上的元素改为9，重量级命令。
    - `blpop age 10`：阻塞版 `lpop`，`age` 为空时，10秒内进行阻塞等待新元素，0表示永远等待。
    - `brpop age 10`: 阻塞版 `rpop`，对空列表在10秒内进行阻塞等待新元素，0表示永远等待。

## 3.5 字符集合set

**概念：**  set类型是一个无序且元素不重复的集合，相当于HashSet，它是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。
- 存储特性：
    - set集合中最大的成员数量是 4294967295。
- 常用命令：`hobby` 集合不存在时自动创建：
    - `sadd hobby a`：向 `hobby` 中添加 `a`，若 `a` 已存在则添加失败返回0，批量添加空格分隔，返回影响数。
    - `srem hobby a`：从 `hobby` 中删除 `a`，批量删除空格分隔，返回影响数。
    - `scard hobby`: 返回 `hobby` 中的元素个数。
    - `sismember hobby a`：判断 `hobby` 中是否存在 `a`，存在返回1，不存在返回0。 
    - `srandmember hobby 5`：随机从 `hobby` 中返回5个元素。
    - `spop hobby`：随机从 `hobby` 中返回并删除（弹出）一个元素。
    - `smembers hobby`：无序返回 `hobby` 中的所有元素，重量级命令。
    - `sdiff hobby1 hobby2`：返回两个set集合的差集，重量级命令。
    - `sinter hobby1 hobby2`：返回两个set集合的交集，重量级命令。
    - `sunion hobby1 hobby2`：返回两个set集合的并集，重量级命令。
    - `sdiff|sinter|sunion hobby1 hobby2 store hobby3`：返回两个set集合的差/交/并集并存入 `hobby3`。

## 3.6 有序字符集合zset

**概念：** zset和集合set一样也是string类型元素的集合，类似于SortedSet，且不允许重复的成员。不同的是zset中的每个元素都会关联一个double类型的带权值score，zset正是通过score来为集合中的成员进行从小到大的排序：
- 存储特性：zset集合的成员是唯一的，但分数score却可以重复。
- 常用命令：`age` 集合不存在时自动创建：
    - `zadd age 1.5 a 1.6 b`：向集合中添加1.5分的a和1.6分的b，同名覆盖，返回影响数。
    - `zrange age 0 3`：升序返回集合中索引0到3范围内的元素，不带分数。
    - `zrange age 0 3 withscores`：升序返回集合中索引0到3范围内的元素，带分数。
    - `zrange age 0 -1`：升序返回集合中所有元素。
    - `zrevrange age 0 -1`：降序返回集合中所有元素。
    - `zrem age a b`：从集合中删除 `a` 和 `b`，返回影响数。
    - `zscore age a`：返回集合中 `a` 的分数，`a` 不存在返回 `nil`。
    - `zincrby age 5 a`：将集合中 `a` 的分数自增5，负数表示自减，返回 `a` 自增/自减后的值。
    - `zcard age`：返回集合中全部元素的个数。
    - `zrank age a`：返回集合中 `a` 的升序排名索引，从0开始。
    - `zrevrank age a`：返回集合中 `a` 的降序排名索引，从0开始。
    - `zrangebyscore age 0 9 withscores`：升序返回集合中0到9分范围内的排名，带分数。
    - `zrevrangebyscore age 9 0 withscores`：降序返回集合中9到0分范围内的排名，带分数。
    - `zcount age 0 9`：返回集合中0到9分范围内有多少元素。
    - `zremrangebyrank age 0 5`：删除集合中排名索引0到5之间的全部元素。
    - `zremrangebyscore age 0 9`：删除集合中分数范围在1到9之间的全部元素。
    - `zinterstore age3 2 age1 age2`：将 `age1` 和 `age2` 两个集合的交集（分数相加）存入 `age3`。
    - `zunionstore age3 2 age1 age2`：将 `age1` 和 `age2` 两个集合的并集（分数相加）存入 `age3`。