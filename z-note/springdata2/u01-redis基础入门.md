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
- 常用配置：`daemonize yes` 表示以守护进程启动redis，但windows下无效：
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

**概念：** redis所有键都是字符串类型，而值则可使用 `string/hash/list/set/zset` 五种自带本地方法的数据类型，除string外的容器型数据结构都可以在添加元素时自动创建，且容器中无元素时自动删除以释放内存：
- `nil` 表示无值，任何变量在没有被赋值之前的值都为 `nil`。

## 3.1 通用命令

**概念：** `keys` 是重量级命令，不在生产环境中对主节点使用，其余都是轻量级命令：
- `help @General/@string`：查看所有通用命令/string命令，其他命令同理。
- `keys 5?[a-z]*`：返回第1位是5，第3位置是字母，其后随意的所有键。
- `dbsize`：返回总键数。
- `exists a`：判断键a是否存在，存在返回1，不存在返回0。
- `del a b`：同时删除键a和键b，返回总影响数，删除失败的键计为0。
- `expire a 20`：设置键a在20秒后过期，成功返回1。
- `ttl a`：返回键a的当前寿命，单位秒，键不存在返回-2，键永生返回-1。
- `persist a`：移除键a的过期时间，使其永生。
- `type a`：返回键a的数据类型，键不存在返回none。

## 3.2 字符串string

**概念：** string类型包括字符串，整数和二进制等，二进制安全，即底层最终都是用字节数组存储的数据，一个string键最多能存512MB的值，常用于缓存，计数器，分布式锁，内存级KB级小文件系统等场景，或用于提取服务集群中的某些公用状态变量以达到"服务无状态"，便于集群扩缩容，打造弹性服务：
- `set a 1`：永久存储a=1，同名覆盖，无论键是否存在都成功，总是返回OK。
- `setnx a 1`：永久存储a=1，同名覆盖，键不存在时才成功并返回1，否则返回0。
- `set a 1 xx`：永久存储a=1，同名覆盖，键存在时才成功并返回OK，否则返回nil。
- `setex a 8 5`：存储a=5，并设置8秒后过期，返回OK。
- `mset a 1 b 2`：批量永久存储a=1和b=2，原子操作节省网络IO次数，重量级命令。
- `get a`：获取a值，键不为string报错，键不存在返回nil。
- `mget a b`：批量获取a和b值，原子操作节省网络IO次数，但为重量级命令。
- `del a`：删除键a，删除成功返回1，键不存在返回0。
- `incr/decr a`：对a值自增/自减1并返回，键不存在时视为a=0，返回 `1/-1`。
- `incrby/decrby a 5`：对a值自增/自减5并返回，键不存在时视为a=0，返回 `5/-5`。
- `incrbyfloat a 1.5`：对a值自增1.5并返回，负数表示自减。
- `getset a 5`：将a值修改为5，并返回a原值，原子组合操作。
- `append a b`：在a值末尾拼接b，返回拼接后的字节长度。
- `strlen a`：返回a值字节数组长度，UTF8编码下中文占3字节，GBK编码下中文占2字节。
- `getrange a 0 9`：返回a值中，索引0到9范围内的所有值。
- `setrange a 0 9`：设置a值中，索引0的值为9。

## 3.3 哈希hash

**概念：** hash类型可视为拥有字符串属性的HashMap容器，每个hash最多存储4294967295个键值对，适合于存储bean对象：
- `hset user a 1`：为user设置a=1属性，成功返回1。
- `hsetnx user a 1`：为user设置a=1属性，若a已存在则失败。
- `hincrby user a 5`：为user中的a值自增5。
- `hincrbyfloat user a 1.5`: 为user中的a值自增1.5。
- `hget user a`：返回user中的a值，值不为string报错，键不存在返回nil。
- `hgetall user`：返回user中所有键值对，执行速度较慢。
- `hdel user a b`：同时删除user中的属性a和属性b，返回总影响数。
- `hexists user a`：判断user中是否存在属性a，存在返回1，不存在返回2。
- `hlen user`：返回user中所有的键值对数。
- `hmget user a b`：批量返回user中a值和b值，原子操作节省网络IO次数，重量级命令。
- `hmset user a 1 b 2`：批量为user设置a=1和b=2，原子操作节省网络IO次数，重量级命令。
- `hkeys/hvals user`：返回user中所有属性/值。

## 3.4 字符列表list

**概念：** list类型可视为有序的LinkedList，允许重复元素，允许双向操作，最多包含4294967295个元素：
- `lpush/rpush age 1 2`：在age头/尾部依次插入1和2，返回插入后长度。
- `lrange age 0 2`：取出age中，索引0到2的全部元素，两端包括，负数视为倒数。
- `lpop/rpop age`：返回并删除（弹出）age头/尾，空列表返回nil。
- `linsert age before/after a b`：在age中的第一个a元素前/后插入b元素，重量级命令。
- `lrem age 5 1`：从左到右删除age中最多5个1，负数从右到左删，0视为全部删除，重量级命令。
- `ltrim age 0 9`：保留age中索引0到9的值，两端包括，其余删除，重量级命令。
- `lindex age 3`：返回age中索引3上的元素，不存在返回nil，重量级命令。
- `llen age`：返回age元素总个数。
- `lset age 3 9`：将age中索引3上的元素改为9，重量级命令。
- `blpop/brpop age 10`：阻塞版lpop/rpop，空列表会在10秒内进行阻塞等待新元素，0表示永远等待。

## 3.5 字符集合set

**概念：** set类型可视为无序的HashSet，元素不允许重复，最多包含4294967295个元素，常用于推荐系统共同好友等场景：
- `sadd hobby a b`：向hobby中添加a元素和b元素，重复添加返回0，返回总影响数。
- `smembers hobby`：返回hobby中的所有元素，重量级命令。
- `srem hobby a b`：从hobby中删除a元素和b元素，返回总影响数。
- `scard hobby`: 返回hobby中的元素个数。
- `sismember hobby a`：判断hobby中是否存在a元素，存在返回1，不存在返回0。 
- `srandmember hobby 5`：随机从hobby中返回5个不重复元素，负数表示返回5个有可能重复的元素。
- `spop hobby`：随机从hobby中返回并删除（弹出）一个元素。
- `sdiff/sinter/sunion h1 h2`：返回h1和h2的差/交/并集，重量级命令。
- `sdiffstore/sinterstore/sunionstore h3 h1 h2`：返回h1和h2的差/交/并集并存入h3，重量级命令。

## 3.6 有序字符集合zset

**概念：** zset类型可视为有序的SortedSet，每个元素都会关联一个double类型的分数score以进行排序，元素不允许重复但是分数可以重复，常用于排行榜等场景：
- `zadd age 1.5 a 1.6 b`：向age中添加1.5分的a元素和1.6分的b元素，同名覆盖，返回总影响数。
- `zrange/zrevrange age 0 3 withscores`：带分数升序/降序返回age中索引0到3的元素，省略withscores则不带分数。
- `zrem age a b`：从age中删除a元素和b元素，返回总影响数。
- `zscore age a`：返回age中a元素的分数，元素不存在返回nil。
- `zincrby age 5 a`：将age中a元素的分数自增5并返回，负数表示自减。
- `zcard age`：返回age中全部元素总数。
- `zrank/zrevrank age a`：返回age中a的升序/降序排名，从0开始。
- `zrangebyscore/zrevrangebyscore age 0 9 withscores`：升序/降序返回age中索引0到9分的排名，带分数。
- `zcount age 0 9`：返回age中0到9分的元素数量。
- `zremrangebyrank age 0 5`：删除age中排名索引0到5的全部元素，返回总影响数。
- `zremrangebyscore age 0 9`：删除age中分数在1到9的全部元素，返回总影响数。
- `zinterstore/zunionstore k3 2 k1 k2`：将k1和k2两个集合的交集/并集存入k3，分数相加。