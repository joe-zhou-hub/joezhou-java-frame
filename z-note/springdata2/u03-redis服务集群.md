# 1. 主从模型

**流程：** redis支持1主N从模型以提高数据容灾性，主从中的数据只能从master流向slave，故主写从读，读写分离：
- 在 `slave` 目录下开发配置文件 `6381/6382.conf`：配置端口号，工作目录，日志，RDB文件：
    - 主从节点不建议部署在同一台机器上。
- 将主从节点配置为windows服务并启动，以6381为例：
    - `redis-server --service-install slave/6381.conf --service-name redis6381`
    - `redis-server --service-start --service-name redis6381`
- `6382 > slaveof 127.0.0.1 6381`：使6382成为6381的从节点，该过程异步执行，立即返回OK：
    - 建立主从关系之前，redis会先清空从节点的所有数据。
    - 后台使用 `bgsave` 命令将数据备份到RDB文件，然后再恢复到slave以完成数同步。
- `6382 > slaveof no one`：使6382脱离主从关系，此后不再接收master同步的数据，但之前同步的数据仍然保留。
- `6381/6382 > info replication`：分别查看主从节点的replication信息。
- `6381 > set a 100`：在主节点中写数据，返回OK。
- `6381 > set a`：在主节点中读数据，返回 `100`。
- `6382 > set a 100`：在从节点中写数据，报错。
- `6382 > get a`：在从节点中读数据，返回 `100`。
- 配置文件方式：若不使用命令，可直接在slave配置文件中额外配置并重启redis节点以搭建主从关系：
    - `masterauth 123`：配置master认证密码，无密码时可省略。
    - `slaveof 127.0.0.1 6381`：配置其所属master的IP和端口号。

# 2. 哨兵模型

**概念：** sentinel哨兵无法操作数据，仅用于提升主从模型的高可用问题，建议搭建多个sentinel作为一个集群使用：
- 开发配置文件 `7007/7008/7009.conf`：配置端口号，工作目录，日志，RDB文件。
- 将3个节点配置为windows服务并启动，以7007为例：
    - `redis-server --service-install slave/7007.conf --service-name redis7007`
    - `redis-server --service-start --service-name redis7007`
- `7008/7009 > slaveof 127.0.0.1 7007`：分别使7008和7009成为7007的从节点。
- 开发哨兵配置文件 `27007/27008/27009.conf`：配置端口号，工作目录，日志：
    - `protected-mode no`：关闭保护模式，否则Jedis无法访问。
    - `sentinel monitor my-master 127.0.0.1 7007 2`：监视以7007为master的主从结构，当累计超过2个主观下线标记时执行故障转移，sentinel支持同时监视多套主从结构，所需需要设置名称以区分。
    - `sentinel down-after-milliseconds my-master 5000`：5秒内ping不通master或直接报错时标记主观下线。
    - `sentinel parallel-syncs my-master 1`：故障转移时最多1个节点同步新的master数据。
    - `sentinel failover-timeout my-master 15000`：故障转移超时时间为15秒。
- 将3个哨兵节点部署为window服务并启动，以27007为例：哨兵节点需额外添加 `--sentinel` 参数：
    - `redis-server --service-install sentinel/27007.conf --sentinel --service-name sentinel27007` 
    - `redis-server --service-start --service-name sentinel27007`
- 查看全部sentinel日志：生成哨兵ID，发现了全部slave，哨兵之间互相发现以保证高可用。
- `27007/27008/27009 > info sentinel`：查看监视信息。
- 手动下线7007，再查看任一sentinel的监视信息，会发现master发生变更。
- 查看全部sentinel日志：假设27007为队长，27008/27009为队员：
    - `z-res/sentinel日志解析.md`
- 重新上线7007，再查看任一sentinel的监视信息，会发现三个sentinel都删除了对7007的主观下线，且其中一个sentinel执行了 `+convert-to-slave` 将7007变更为当前master的slave。
- 开发 `c.j.s.JedisSentinelTest`：
    - `new JedisSentinelPool()` 构建哨兵连接池，需要参数主从名，哨兵地址端口的Set集合以及连接池配置实例。

# 数据分区方式
- 节点取余：
    - 客户端分片：哈希 + 取余。
    - 缺点：节点伸缩时数据需要迁移，建议翻倍扩容以减轻迁移量。
- 一致性哈希：
    - 预设一个token环，数值范围是0~2^32
    - 在token环上均匀设置N个节点。
    - 对key值求hash值，一定分布在两个节点范围内。
    - 按照顺时针方向找到离这个hash值最近的节点。
    - 添加新节点时只会影响到两个节点，而不会影响其他的节点。
    - 缺点：数据分步可能不均匀，扩容也建议均匀。
- 虚拟槽分区：redis-cluster使用：
    - 预设虚拟槽0-16383范围，每个槽映射一个数据子集，一般比节点数大。
    - 对key值求hash值对16383取余，一定分布在两个节点范围内。











# 分片集群

**概念：** 搭建分片集群主要为了分担redis服务器压力，集群仅能使用一个库db0：
- 每个主节点负责一个槽，槽的分配尽量均匀，否则容易造成数据倾斜，从节点不带槽。
- 主节点之间互相通信，互知槽范围。
- 每个主节点都可读可写，从节点不可写，读时默认重定向到数据所在主节点后再读，若需直接在从节点读，需要每次连接从节点后先执行 `readonly` 命令再读。
- 客户端访问一个节点时，若数在该节点中直接返回，若不在该节点中则该节点会返回数据在哪个节点中（不会帮你跳转），建议使用智能客户端，即客户端提前获知所有节点的槽范围，访问时直接访问到对应的节点以解决效率问题。


- cluster架构：node + meet + 指派槽 + 主从复制（不使用哨兵）
    - cluster-enabled:yes：以集群方式启动
    - node-a meet node b ：搭建ab节点互K通


## 原生安装

**流程：**
- 配置开启节点：配置6个，从7001-7006
    - `port 7001`：端口号。
    - `dir ./cluster`：工作目录，该文件夹需提前创建。
    - `dbfilename dump-7001.rdb`：RDB文件。
    - `logfile 7001.log`：日志名。
    - `cluster-enabled yes`：开启集群模式。
    - `cluster-config-file nodes-7001.conf`：集群配置文件。
    - `cluster-node-timeout 15000`：集群创建超时时间。
    - `cluster-require-full-coverage no`：默认yes，表示当集群中有一个节点故障则整体不对外服务，建议改为no。
- 将6个节点配置为windows服务（可选）：
    - `redis-server --service-install cluster/7001.conf --service-name redis7001` 
- 启动6个点，预设3主3从：启动日志中会有 [cluster] 标记，表示以集群的方式启动：
    - `redis-server --service-start --service-name redis7001`：启动7001节点。
    - `redis-cli -p 7001 cluster nodes`：查看7001节点的集群节点连接情况。
    - `redis-cli -p 7001 cluster info`：查看7001节点的集群信息。
    - 也可以直接查看 `nodes-7001.conf` 文件以查看7001节点的集群信息
- meet：使用其中一个节点如7001去meet其他5个节点，此时6个节点完成互通：
    - `redis-cli -p 7001 cluster meet 127.0.0.1 7002`：7001见面7002。
- 分配槽：未分配槽的节点不可使用，使用脚本循环分配槽，否则工作量巨大：
    - bat脚本格式：`for /L %循环变量 in (起始值,变化值,终止值) do 命令`，可使用 `ehco %循环变量` 测试循环。
    - `for /L %i in (0,1,5461) do redis-cli -h 127.0.0.1 -p 7001 cluster addslots %i`：为7001分配槽0-5461。
    - `for /L %i in (5462,1,10922) do redis-cli -h 127.0.0.1 -p 7002 cluster addslots %i`：为7002分配槽5462-10922。
    - `for /L %i in (10923,1,16383) do redis-cli -h 127.0.0.1 -p 7003 cluster addslots %i`：为7003分配槽10923-16383。
    - `redis-cli -p 7001 cluster slots`：查看7000的槽信息，以及主从配置信息。
- 配置主从：3从1，4从2，5从3：
    - `redis-cli -p 7000 cluster nodes`：展示所有节点，主要关注第一列的nodeId
    - `redis-cli -p 7004 cluster replicate 7001的nodeId`
    - `redis-cli -p 7005 cluster replicate 7002的nodeId`
    - `redis-cli -p 7006 cluster replicate 7003的nodeId`
    - `redis-cli -p 7000 cluster nodes`：展示所有节点，关注master到slave的改变
    - `redis-cli -c -p 7001`：`-c` 以集群模式连接时，可自动重定向到键对应槽位所在的节点并执行命令。
    - `cluster keyslots money`：查看键 `money` 经过hash计算后的槽位。
    - `set money 100`，添加成功，观察到该变量被重定向的某节点的槽位中并存储。
    - `redis-cli -c -p 7001`：`-c` 以集群模式连接时，可自动重定向到键对应槽位所在的节点并执行命令。
    - `get money`：成功获取该变量。K

## ruby安装

**流程：**
- 将redis根目录整体拷贝6份，视为6个redis实例，取名 `redis-7011` 到 `redis-7016`。
- 分别在6个redis实例中添加配置文件，取名 `7011.conf` 到 `7016.conf`。
- 分别修改6个redis实例的配置，参考原生安装，节点配置文件尽量保持统一，且需要定期检查一致性，否则有可能发生数据倾斜。
- 将6个redis实例配置为window服务。
- 安装ruby：勾选后两项添加环境变量以及关联相关文件：
    - `z-res/rubyinstaller-2.2.4-x64.exe`
- 下载ruby驱动(https://rubygems.org/gems/redis/versions)，选择3.2.1版本，并粘贴到ruby根目录中。
    - `z-res/redis-3.2.1.gem`
- 在ruby根目录中安装驱动：cmd: `gem install redis`
- 下载并安装集群脚本redis-trib.rb，该文件在redis源码的src文件夹中：
    - `z-res/redis-win-3.2.100.zip`  
- 将 redis-trib.rb分别拷贝到6个redis实例中。
- 启动6个redis实例：
    - `redis-server --service-start --service-name redis7011`：启动7011节点
- 在任意一个redis实例的根目录中执行：`ruby redis-trib.rb create --replicas 1 127.0.0.1:7011 127.0.0.1:7012 127.0.0.1:7013 127.0.0.1:7014 127.0.0.1:7015 127.0.0.1:7016` 以使用该脚本搭建集群，数字1表示1主1从，0表示没有从节点。
- 出现 Can I set the above configuration? (type 'yes' to accept): 观察集群和主从信息，输入yes，回车。
- 配置成功。
- `redis-cli -p 7011 cluster nodes`：展示所有节点，关注master到slave的改变
- `ruby redis-trib.rb info 127.0.0:7011`：在任意节点访问数据分布，槽分布和主从信息。
- `ruby redis-trib.rb rebalance 127.0.0:7011`：在任意节点进行数据均衡，慎用。
- `redis-cli -c -p 7011`：`-c` 表示以集群方式登录7011节点。
- `set money 100`，添加成功，观察到该变量被重定向的某节点的槽位中并存储。
- `redis-cli -c -p 7012`：`-c` 切换7012节点。
- `get money`：成功获取该变量。

## 集群扩容

**流程：** 集群伸缩指得就是槽和数据在节点之间的迁移，数据量越大，整个迁移过程比较慢，但不会影响程序正常运行：
- 将redis根目录整体拷贝2份 `redis-7017` 和 `redis-7018`，预设1主1从，配置和其他节点一致，并分别粘贴一个 `redis-trib.rb`。
- 将2个新节点部署成windows服务并启动。
- `redis-cli -p 7011 cluster meet 127.0.0.1 7017`：将7017节点加入集群。
- `redis-cli -p 7011 cluster meet 127.0.0.1 7018`：将7018节点加入集群。
- `redis-cli -p 7018 cluster replicate 7017的nodeId`：将7018配置为7017的从节点。
- `redis-cli -p 7011 cluster nodes`：查看集群节点，发现7017和7018节点未分配槽，即无法读写数据。
- `ruby redis-trib.rb reshard 127.0.0.1:7017`：
    - how many slots do you want to move？给新ID总共分配 16384/4=4096 个槽
    - what is the receiving node ID：输入7017节点ID，该节点用于接收槽数据。
    - please enter all the source node IDs：输入all，表示所有节点都为新节点分配槽数据。
    - do you want to proceed with the porposed reshard plan：输入yes继续任务。
- `redis-cli -p 7011 cluster nodes`：查看集群节点，发现7017有三段槽，分别来自于其他所有节点。

## 集群收缩

**流程：** 删除7017和7018节点，为了避免触发故障转移，先删除从节点，后删除主节点：
- `ruby redis-trib.rb reshard --from 7017的id --to 7011的id --slots 1366 127.0.0.1:7011` 数据迁移：
    - 在任意节点如7011上执行，将7017的1366个槽迁到7011。
    - 在任意节点如7011上执行，将7017的1365个槽迁到7012。
    - 在任意节点如7011上执行，将7017的1365个槽迁到7013。
- `ruby redis-trib.rb del-node 127.0.0.1:7018 7018的id`：将7018从节点从集群中删除。
- `ruby redis-trib.rb del-node 127.0.0.1:7017 7017的id`：将7017主节点从集群中删除。
- `redis-cli -p 7011 cluster nodes`：查看集群节点，发现7018和7017已被移除集群。

## 多节点操作

**流程：** 在分片集群模式下，`keys *` 命令仅能展示当前节点的全部key，若需要展示全部节点的key，则：
- 开发测试方法 `c.j.s.jedis.JedisClusterTest.operateOnAllNodes()`：
    - `jedisCluster.getClusterNodes()`：获取所有的集群节点，包括从节点。
    - `jedis.info("replication")`：返回节点 `replication` 信息。

## 故障转移

**流程：** cluster集群内部实现了故障转移机制，无需使用sentinel：
- 含槽主节点node-b通过ping/pong机制发现与node-a通信超时，对其标记主观下线 `pfail`。
- 含槽主节点node-c通过ping/pong机制发现与node-a通信超时，对其标记主观下线 `pfail`。
- 当半数以上含槽主节点都对node-a标记了 `pfail` 时，主观下线升级为客观下线，并向集群广播。
- 所有node-a的从节点准备选举，但与node-a很久不联系的从节点没有选举资格：
    - 与主节点最后一次通信时间超过 `cluster-node-timeout * cluster-slave-validity-factor` 时丧失资格。
- 所有其他主节点对node-a的所有从节点投票，与node-a最后通信时间越靠前的优先级越高。
- 从节点选举成功，执行 `slave no one` 抛弃从节点身份。
- 执行 `clusterDelSlots` 命令将node-a的槽清空。
- 执行 `clusterAddSlots` 命令将node-a的槽重新分配给新的主节点，并向集群广播。
- 开发测试方法 `c.j.s.jedis.JedisClusterTest.failover()`：
    - 连入集群并利用循环每隔1秒钟向集群发送指令。
    - 下线主节点如 `redis7011`，查看节点选举和故障转移情况。
    - 恢复主节点如 `redis7011`，查看节点选举和故障转移情况。