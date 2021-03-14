# 1. 概念入门

**概念：** [ElasticSearch](https://www.elastic.co) 是基于lucene核心并由java开发的一款开源的检索引擎，支持全文检索，分布式检索，数据统计分析，内容纠错和提示等功能，可扩展到上百台服务器，可处理PB级数据，且检索延迟通常在1秒以内，常用于日志搜索，数据聚合，数据监控，报表统计分析等业务：
- 面向文档：ES针对存储的每个文档 `document` 的内容进行索引，检索，排序，过滤等操作，索引在ES中有两种意思：
    - 索引(n)：对应DB实例，是一个存储 `document` 的地方，复数词为 `indices/indexes`。
    - 索引(v)：将一个 `document` 存储到索引(n)中的操作，类似DB的 `insert/update` 操作。
- 组成结构：
    - `cluster` 集群：1或N个ES节点可构成1个ES集群，默认名 `elasticsearch`，集群间共享数据，均可操作数据。
    - `node` 节点：1个启动的ES服务即为一个ES节点，默认名随机生成，用于存储，索引，搜索数据。
    - `index` 索引：对应DB实例，包括1或N个 `type`，索引名建议全小写。
    - `type` 文档类别：对应DB表，默认名 `_doc`，ES7+已废弃 `type`：
        - ES5中1个 `index` 允许存在多个 `type`。
        - ES6中1个 `index` 仅允许存在一个 `type`。
    - `document` 文档：对应DB表行 `row`，是构成 `index` 的最小单元，采用JSON格式。
    - `field` 字段：对应DB表列 `column`，是构成 `document` 的单元。
    - `mapping` 索引映射：对应DB约束，用来约束 `field` 类型和某些数据的使用规则以提升搜索性能。
    - `shard` 分片：将 `index` 拆成多个 `shard` 可提升数据规模上（水平切数据）和性能上（并行执行）的提升：
        - 分片数量必须在 `index` 创建时指定，默认为1个，`index` 创建后不可动态修改。
        - 每个 `shard` 仍是一个功能完整的 `index`，最多存放 `Integer.MAX_VALUE - 128` 个document。
    - `replica` 分片副本：每个 `shard` 可以有1或N个副本 `replica shards` 以提升高可用性和并发性能：
        - 分片副本数量可以在 `index` 创建时指定，默认为1个，`index` 创建后仍可动态修改。

# 2. ES服务端安装

**概念：** [ES](https://www.elastic.co/downloads/elasticsearch[Download]) 的安装需要正确的JDK环境：
- 将ES安装包解压缩到硬盘后双击 `config/elasticsearch.bat` 以启动服务器，`started` 表示服务器启动成功。
- 访问日志中 `publish_address` 展示的默认地址端口：
    - psm: `localhost:9200`：JSON格式返回当前ES服务的基本信息。
- 可视化界面：可以在谷歌浏览器上安装ES的head插件，完成图形化界面的效果：
    - `z-res/es可视化界面.md`

# 3. ES集群搭建

**流程：** 创建 `D:\\es-cluster` 集群目录：
- 拷贝两个 `elasticsearch-7.6.0` 根目录到集群目录内，视为两个ES节点，分别命名 `es-node-a/es-node-b`。
- 将两个ES节点中的 `data` 目录全部删除以保证节点数据一致性。
- 修改两个ES节点的配置 `config/elasticsearch.yml`：不要在yml中添加注释：
    - `cluster.name`：两个ES节点的集群名必须一致，默认 `elasticsearch`。
    - `node.name`：两个ES节点的名称必须不一致。
    - `node.master`：为true表示该节点拥有成为master的资格。
    - `node.data`：为true表示该节点允许存储数据，默认true。
    - `network.host`：该ES节点所在的服务器IP，设置为 `0.0.0.0` 表示允许所有IP对我访问。
    - `http.port`：该ES节点的端口号，默认9200。
    - `transport.tcp.port`：集群间通信端口号，与ES节点端口号不同。
    - `discovery.zen.ping.unicast.hosts`：集群节点列表，使用通信端口，两个ES节点相同：
        - 如 `[127.0.0.1:9201, 127.0.0.1:9202]`。
    - `cluster.initial_master_nodes`：配置初始master的IP和端口，也可使用master的 `node.name`。
    - `http.cors.enabled`：为true表示允许被跨域访问，默认false。
    - `http.cors.allow-origin`：`"*"` 表示支持所有域名。
- 同时启动两个ES节点:
    - psm: `localhost:9201/9202`：查看两个节点是否均能启动成功。
    - psm: `localhost:9201/_cat/health?v`：查看ES服务的健康状态。
    - psm: `localhost:9201/_cat/nodes?v`：查看ES服务的节点列表。








