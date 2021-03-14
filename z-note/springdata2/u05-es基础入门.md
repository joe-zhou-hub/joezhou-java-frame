# 1. 概念入门

**概念：** ElasticSearch，简称ES，是一个开源的高扩展的分布式全文检索引擎，基于JAVA语言开发，使用Lucene作为其核心底层，但其目的是通过简单的Restful-API来隐藏Lucene的复杂性，从而让全文搜索变得简单：
- 技术特点：
    - 支持全文检索/结构化检索/分布式检索等。
    - 支持数据统计/数据分析等。
    - 支持检索内容纠错，检索内容提示等。
    - 扩展性很好，可以扩展到上百台服务器。
    - 可以近乎实时的存储和检索数据，检索延迟通常被控制在是1秒以内。
    - 可以处理PB级别的数据。
- 使用场景：日志搜索，数据聚合，数据监控，报表统计分析等。
- 使用者： GitHub/维基百科/StackOverflow等。
- 对比Solr：
    - `Solr` 底层都是Lucene。
    - `Solr` 更针对小数据量，`ES` 更针对大数据量。
    - `Solr` 利用 `Zookeeper` 进行分布式管理，`ES` 自带分布式协调管理功能。
    - `Solr` 支持更多格式的数据，`ES` 仅支持JSON文件格式。
    - `Solr` 官方提供的功能更多，`ES` 更注重于核心功能，高级功能多由第三方插件提供。
    - `Solr` 在传统的搜索应用中表现更优，`ES` 在处理实时搜索应用时更优。

# 2. ES存储常用概念

**概念：** ES是面向文档(document oriented)的，它可以存储整个对象或文档(document)，然后我们可以对ES中的每个文档的内容进行索引/检索/排序/过滤等操作。
- 索引这个词在ES中有两种意思：
    - 索引（名词）：类似于关系型数据库中的数据库 ，是一个存储关系型文档的地方，复数词为 `indices` 或 `indexes `
    - 索引（动词）：索引一个文档表示存储一个文档到一个索引（名词）中，类似于 `INSERT` 或 `UPDATE` 操作。
- ES的组成结构如下表：
    - `cluster` 集群：一个或多个节点的集合被称为一个ES集群，默认名 `elasticsearch`，集群之间共享数据，一起提供索引和搜索功能。
    - `node` 节点：一个启动的ES服务即为一个ES节点，默认名是个UUID，节点用于保存数据并具有索引和搜索的能力。
    - `index` 索引：对应数据库 `database`，包括1或N个 `type`，索引名建议全小写。
    - `type` 文档类别：对应数据表 `table`，默认名 `_doc`
        - `ES5.x`：一个 `index` 允许多个 `type`。
        - `ES6.x`：一个 `index` 只允许一个 `type`。
        - `ES7.x`：将废弃 `type`。
    - `document`：文档：对应数据库表行记录 `row`，是构成 `index` 的最小单元，采用JSON格式。
    - `field` 字段：对应数据库表列 `column`，是构成 `document` 的单元。
    - `mapping` 索引映射：对应约束 `key`，用来约束 `field` 的类型和某些数据的使用规则，按照规则处理数据可以提升性能。
    - `shard` 分片：`shard` 是ES数据存储的最小单位，可以将 `index` 拆成多个 `shard`，每个 `shard` 仍是一个功能完整的 `index`：
        - 分片数量可以在一个 `index` 创建时指定，默认为1个。
        - 分片数在 `index` 创建完毕之后不可更改。
        - `shard` 带来数据规模上（数据水平切分）的提升。
        - `shard` 带来性能上（并行执行）的提升。
        - 一个分片最多存放 `Integer.MAX_VALUE - 128 = 2147483519` 个document。
    - `replica` 分片副本：每个 `shard` 可以有一个或多个副本 `replica shards`
        - 副本数量可以在一个 `index` 创建时指定，默认为1个。
        - 副本数在 `index` 创建完毕之后可以动态更改。
        - 副本用于提升 `shard` 的高可用性，用于提升搜索时的并发性能（搜索可以在全部分片上并行执行）。

# 3. ES服务端安装

**概念：** [ES](https://www.elastic.co/downloads/elasticsearch[Download]) 是使用java开发的，所以安装ES之前保证JDK安装完毕，并正确的配置好JDK环境变量。
- 将ES文件夹解压缩，并配置环境变量。
- 启动ES服务：
    - 可以双击启动 `bin/elasticsearch.bat`。
    - 可以执行cmd命令 `elasticsearch`。
- 日志展示 `started` 表示服务器启动成功。
- 日志中的 `publish_address` 表示服务器访问接口，默认 `127.0.0.1:9200`。
- 测试：
    - psm: `GET > localhost:9200`：返回当前ES服务的基本信息，JSON格式。
    - psm: `GET > localhost:9200/_cat/health?v`：查看ES服务的健康状态。
    - psm: `GET > localhost:9200/_cat/nodes?v`：查看ES服务的节点列表。

# 4. ES集群搭建

**概念：** 建议使用多台机器来搭建ES集群，我们这里使用一台机器来搭建伪集群。
1. 创建 `elasticsearch-cluster` 集群文件夹：`D:\coder\java\elasticsearch\elasticsearch-cluster`。
2. 复制两个 `elasticsearch-7.6.0` 根目录到集群文件夹，分别取名 `elasticsearch-main` 和 `elasticsearch-01`。
3. 将两个文件夹中的 `data` 文件夹全部删除，保证数据一致性。
4. 修改每台ES服务器实例的配置文件如下，不要在yml中添加注释
    - `cluster.name`：集群名必须一致，默认 "elasticsearch"
    - `node.name`：节点名必须不一致
    - `node.master`：为true表示这个节点拥有成为主节点的资格
    - `node.data`：为true表示这个节点可以存储数据，默认为true
    - `network.host`：ES节点所在机器的IP地址，也可以设置成 `0.0.0.0`，代表所有ip可以访问
    - `http.port`：相同IP下端口号必须不一致
    - `transport.tcp.port`：集群之间的通信端口，相同IP下必须不一致
    - `discovery.zen.ping.unicast.hosts`：集群中的IP列表
    - `cluster.initial_master_nodes`：指定初始主节点IP和端口号，也可以使用主节点的 `node.name`
    - `http.cors.enabled`：为true表示允许跨域访问（其他主机的浏览器可以访问），默认false
    - `http.cors.allow-origin`：`"*"` 表示支持所有域名

**配置：** elasticsearch-01/config/elasticsearch.yml
```txt
cluster.name: my-es7-cluster
node.name: node-main
node.master: true
node.data: true
network.host: 127.0.0.1
http.port: 9200
transport.tcp.port: 9300
discovery.zen.ping.unicast.hosts: ["127.0.0.1:9300", "127.0.0.1:9301"]
cluster.initial_master_nodes: ["127.0.0.1:9300"]
http.cors.enabled: true 
http.cors.allow-origin: "*"
```

**配置：** elasticsearch-02/config/elasticsearch.yml
```txt
cluster.name: my-es7-cluster
node.name: node-01
node.master: true
node.data: true
network.host: 127.0.0.1
http.port: 9201
transport.tcp.port: 9301
discovery.zen.ping.unicast.hosts: ["127.0.0.1:9300", "127.0.0.1:9301"]
cluster.initial_master_nodes: ["127.0.0.1:9300"]
http.cors.enabled: true 
http.cors.allow-origin: "*"
```

> 同时启动两个ES节点的elasticsearch.bat文件，测试接口：http://localhost:9200 和 http://localhost:9201。

# 5. ES图形化界面插件安装

**概念：** 
- ES不同于Solr自带图形化界面，但可以在谷歌浏览器上安装ES的head插件，完成图形化界面的效果，完成索引数据的查看。
- 安装ES的head插件之前，需要先安装node.js和grunt。
    - [node-v8.9.4-x64.msi](http://note.youdao.com/noteshare?id=5163e1f8877e8f3e630af4d2afed268a&sub=B5BABFA6C4764649BD472446326A188D)
    - [elasticsearch-head-master.zip](http://note.youdao.com/noteshare?id=03c8704be351bf9f084f2baaa0b63768&sub=FC42C665121C42519D191839FCEB0E90)

**安装步骤：** 
1. 安装node，下一步式安装直到安装完毕。
2. CMD中使用 `node -v` 命令查看node是否安装成功。
3. 安装grunt：CMD中执行 `npm install -g grunt-cli`。
    - 成功：`added 100 packages in 40.123s` 
4. 使用 `grunt -version` 命令查看grunt是否安装成功。
    - 成功：`grunt-cli v1.2.0`
5. 安装 `elasticsearch-head-master` 免安装版。
6. 修改 `elasticsearch-head-master` 根目录下的 `Gruntfile.js`，添加 `hostname` 如下：

**配置：** Gruntfile.js
```js
connect: {
	server: {
		options: {
		    hostname: '*',
			port: 9100,
			base: '.',
			keepalive: true
		}
	}
}
```

7. CMD进入到 `elasticsearch-head-master` 根目录下。
8. 执行 `npm install` 开始安装。
    - 成功：`added 516 packages in 1100.17s`
9. 执行 `grunt server` 或 `npm run start` 运行插件（每次启动都要执行）。
    - 成功：`Started connect web server on http://localhost:9100`
10. 测试接口：`localhost:9100`。






