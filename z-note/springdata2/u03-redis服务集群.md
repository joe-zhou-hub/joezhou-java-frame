# 单机问题
 
**概念：** 单机redis的瓶颈在于无法容灾，容量有限以及QPS瓶颈，主从复制就可以让redis高可用起来，redis支持一主多从结构，每个从节点都从主节点中同步数据（备份）：
- 一个master主节点可以有多个slave从节点，但一个slave从节点只能有一个master主节点。
- 数据流是单向的，只能从主节点master到从节点slave，所以一般从负责读，主负责写，读写分离。
- 主从节点是不建议部署在一台机器上的。
- 主从复制的底层其实就是 `bgsave` 命令备份主节点数据到RDB文件，然后从节点从RDB文件进行恢复（从日志可见），即使关闭了RDB配置，也一样。

# 主从配置

搭建主从主要为了服务的高可用，要求主节点和从节点数据一致。

概念：实现主从配置有两种方式，通过命令和通过配置文件，命令可以随时进行，而配置需要在redis启动前，或者修改过后重启redis才可以生效：
- `slaveof` 命令：在从节点中执行 `slaveof 主节点ip 主节点port` 即可成为其从节点：
    - 该过程是异步的，立刻返回OK。
    - 该过程会先清空从节点中所有的数据，然后再建立主从关系。
- 在从节点中执行 `slaveof no one` 可脱离主从关系：
    - 脱离后从节点不再接收主节点同步的数据，但之前同步的数据仍然保留。
    - 脱离后的从节点可以通过 `slaveof` 切换新的主节点。
- 在配置文件中配置：再redis启动之前添加配置信息：
    - `slaveof 主节点ip 主节点port`
    - `slave-read-only yes`：配置从节点只负责读。

流程：假设6379为master，6380为slaveof
- 拷贝两份配置文件：redis-6379.xml/redis-6380.xml
- 启动redis-6379
    - 分别连接redis-6379和redis-6380
    - 分别使用 `info replication` 查看role信息。
- 在redis-6379中 `set name zhaosi`
- 在reids-6380中 `get name` 发现可以得到 `zhaosi`，主从搭建成功。
- 在redis-6380中 `set name zhaosi`，报错，从节点只读。
- 

修改 reids-6379.xml 主节点配置文件
```
port=6379
logfile "6379.log"

# 修改一下RDB文件名，否则主从节点的RDB会混在一起，不利于主从复制
dbfilename dump-6379.rdb
```

修改 reids-6380.xml 从节点配置文件
```
port=6380
logfile "6380.log"

# 修改一下RDB文件名，否则主从节点的RDB会混在一起，不利于主从复制
dbfilename dump-6380.rdb

# 如果主节点设置了密码，则从节点必须设置该项
masterauth joezhou92

slaveof 127.0.0.1 6379
```

# 全量复制

概念：全量复制的开销很大，包括bgsave开销，RDB文件的网络传输开销，从节点清空自己数据的开销和从节点加载RDB时间开销，全量复制的过程中如果主从之间的网络抖动，则有可能会丢失一部分数据。

流程：
- slave向master自动内部发送 `psync run_id offset` 命令：
    - 主节点每次启动都会生成一个随机的 `run_id`，重启更换，slave若不知道master的 `run_id`（比如master重启，或第一次主从通信）则会发送一个?。
    - 偏移量可以理解为当前数据的光标位置，slave偏移量和master偏移量一致时，表示数据完全同步，slave若不知道master的 `offset`（比如master重启，或第一次主从通信）则会发送 -1。
- master接收到的 `run_id` 为问号，且offset为-1，则决定执行全量复制操作。
- master向slave返回自己的 `run_id` 和 `offset`。
- slave保存主节点的基本信息，包括 `run_id` 和 `offset`。
- master执行 `bgsave` 命令，生成一个RDB文件：
    - 此时master执行的新命令都会被保存到一个 `repl_back_buffer` 缓冲区中。
- master将RDB发送给slave。
- master将缓冲区中的数据发送给slave。
- slave清空自己的旧数据。
- slave加载RDB文件。
- slave加载缓冲区中的数据。
- 全量复制完成。



# 哨兵模式

**概念：** sentinel哨兵部署模式解决了redis主从复制模型的高可用问题，sentinel就是一个特殊的redis，但不能存值取值，一般会搭建多个sentinel作为一个集群使用：
- master节点：7007.conf
    - `port 7007`：端口。
    - `logfile 7007.log`：日志位置。
    - `dbfilename dump-7007.rdb`：RDB文件名。
    - `dir ./sentinel`：工作目录。
- slave节点：7008/7009.conf，以7008为例：
    - `port 7008`：端口。
    - `logfile 7008.log`：日志位置。
    - `dbfilename dump-7008.rdb`：RDB文件名。
    - `dir ./sentinel`：工作目录。
    - `slaveof 127.0.0.1 7007`：指定为7007的从节点。
- sentinel节点：27007/27008/27009.conf，以27007为例：
    - `port 27007`：端口。
    - `logfile 27007.log`：日志位置。
    - `protected-mode no`：必须关闭保护模式，否则Jedis无法访问。
    - `sentinel monitor master-a 127.0.0.1 7007 2`：监视信息：
        - `master-a`：sentinel可以监视多套主从结构，以此名区分。
        - `127.0.0.1 7007`：sentinel初始监视的master节点信息。
        - `2`：至少有2个sentinel认定该master节点失效才会执行故障转移。
    - `sentinel down-after-milliseconds master-a 5000`：对master节点5秒ping不通或直接返回错误则认定它失效。
    - `sentinel parallel-syncs master-a 1`：故障转移时，最多同时有1个redis同步新的master节点数据（串行同步）。
    - `sentinel failover-timeout master-a 15000`：15秒内故障转移未完成则超时失败。
    - `dir ./sentinel`：工作目录。
- 将6个节点部署为window服务并启动（注意sentinel部署需要额外 `--sentinel` 参数），启动所有节点并ping通：
    - `redis-server --service-install sentinel/7007/7008/7009.conf --service-name redis7007/7008/7009`
    - `redis-server --service-install sentinel/27007/27008/27009.conf --sentinel --service-name sentinel27007/27008/27009` 
    - `redis-server --service-start --service-name redis7007/7008/7009` 
    - `redis-server --service-start --service-name sentinel27007/27008/27009`
    - `redis-cli -p 7007/7008/7009/27007/27008/27009` - `ping`
- 查看三个sentinel节点的日志：发现sentinel节点启动后日志被重写了一些信息：
    - `sentinel myid`：启动后生成一个ID用于唯一标识和互相发现。
    - `known-slave`：启动后自动发现了master节点的slave节点信息。
    - `known-sentinel`：sentinel节点可以互相发现，以保证高可用。
- 查看7007是否为主节点：
    - `redis-cli -p 6379`
    - `info replication`
- 查看任一sentinel节点的监控信息：
    - `26379 > info sentinel`：看出被监控的master主节点为7007。
- 将7007节点下线，再次查看任一sentinel节点的监控信息：
    - `26379 > info sentinel`：看出被监控的master主节点更换为7008/7009，完成故障转移。
- 寻找并查看sentinel队长的日志：假设队长为7007
    - `+sdown master ..7007`：主观下线，我认定master节点7007失效了。
    - `+odown master ..7007 quorum 2/2`：客观下线，有两个sentinel认定7007失效了，那它确实失效了。
    - `+try-failover`：尝试故障转移，只有一个sentinel执行故障转移操作。
    - `+vote-for-leader xxx 1`：投1票给xxx作为sentinel队长。
    - `+elected-leader`：当选队长。
    - `+selected-slave`：选择一个slave节点，准备将其晋升为新的master节点。
    - `+failover-state-send-slaveof-noone`：对该slave节点发送 `slaveof-noone` 命令。
    - `+failover-state-wait-promotion`：等待该slave节点晋升（执行 `slaveof-noone` 命令）。
    - `+promoted-slave`：晋升slave节点为master节点。
    - `+slave-reconf-sent/inprog/done`：重写slave节点的配置。
    - `-odown master 7007`：切换前，删除对master节7007的客观下线标识，以便于它将来回归。
    - `+failover-end`：故障转移完成。
    - `+switch-master 7007 7008`：完成master节点的从7007到7008的切换。
    - `+slave slave 7009`：使7009成为7008的子节点。
    - `+slave slave 7007`：使7007成为7008的子节点，前提是7007复活。
    - `+sdown slave`：添加对7007子节点（此时它已降级为slave子节点）的主观下线标识。
- 查看其他两个sentinel队员的日志：
    - `config-update-from`：接收和同步sentinel队长的更新信息。
- 重新上线7007节点，再次查看任一sentinel节点的监控信息：
    - 三个sentinel都删除了对7007子节点的主观下线。
    - 其中一个sentinel执行了 `+convert-to-slave` 将7007转换成了当前master节点7008的slave节点。

源码：JedisSentinelTest

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

**概念：** 搭建分片集群主要为了分担redis服务器压力。
- 每个节点负责一个槽
- 节点之间互相通信，互知槽范围
- 每个节点都可读可写
- 客户端访问一个节点时，若数在该节点中直接返回，若不在该节点中则该节点会返回数据在哪个节点中（不会帮你跳转），建议使用智能客户端，即客户端提前获知所有节点的槽范围，访问时直接访问到对应的节点以解决效率问题。

- cluster架构：node + meet + 指派槽 + 主从复制（不使用哨兵）
    - cluster-enabled:yes：以集群方式启动
    - node-a meet node b ：搭建ab节点互通

# 原生安装
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
    - `get money`：成功获取该变量。

# ruby安装

**流程：**
- 将redis根目录整体拷贝6份，视为6个redis实例，取名 `redis-7011` 到 `redis-7016`。
- 分别在6个redis实例中添加配置文件，取名 `7011.conf` 到 `7016.conf`。
- 分别修改6个redis实例的配置，参考原生安装。
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
- `redis-cli -c -p 7011`：`-c` 表示以集群方式登录7011节点。
- `set money 100`，添加成功，观察到该变量被重定向的某节点的槽位中并存储。
- `redis-cli -c -p 7012`：`-c` 切换7012节点。
- `get money`：成功获取该变量。

# 集群扩容

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

# 集群收缩

**流程：** 删除7017和7018节点，为了避免触发故障转移，先删除从节点，后删除主节点：
- `ruby redis-trib.rb reshard --from 7017的id --to 7011的id --slots 1366 127.0.0.1:7011` 数据迁移：
    - 在任意节点如7011上执行，将7017的1366个槽迁到7011。
    - 在任意节点如7011上执行，将7017的1365个槽迁到7012。
    - 在任意节点如7011上执行，将7017的1365个槽迁到7013。
- `ruby redis-trib.rb del-node 127.0.0.1:7018 7018的id`：将7018从节点从集群中删除。
- `ruby redis-trib.rb del-node 127.0.0.1:7017 7017的id`：将7017主节点从集群中删除。
- `redis-cli -p 7011 cluster nodes`：查看集群节点，发现7018和7017已被移除集群。