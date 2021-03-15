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

## 1.1 ES服务端安装

**概念：** [ES](https://www.elastic.co/downloads/elasticsearch[Download]) 的安装需要正确的JDK环境：
- 将ES安装包解压缩到硬盘后双击 `config/elasticsearch.bat` 以启动服务器，`started` 表示服务器启动成功。
- 访问日志中 `publish_address` 展示的默认地址端口：
    - psm: `localhost:9200`：JSON格式返回当前ES服务的基本信息。
- 可视化界面：可以在谷歌浏览器上安装ES的head插件，完成图形化界面的效果：
    - `z-res/es可视化界面.md`

## 1.2 ES集群搭建

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

# 2. REST基本API

**概念：** REST请求中，GET用于查询，PUT用于添加/修改，DELETE用于删除，支持使用命令行或postman操作数据：
- 启动es9200节点。
- 创建索引：重复创建报错：
    - cmd: `curl -XPUT localhost:9200/index_a?pretty`：
        - `-XPUT` 用于设置请求类型，必须大写。
        - `?pretty` 用于格式化输出JSON数据。
    - psm: `put > localhost:9200/index_b`
- 查看单个索引：
    - cmd: `curl -XGET localhost:9200/index_a?pretty`
    - psm: `get > localhost:9200/index_b`
- 查看节点中全部索引：  
    - cmd: `curl -XGET localhost:9200/_cat/indices?v`
    - psm: `get > localhost:9200/_cat/indices?v`   
- 删除索引：索引不存在时报错：
    - cmd: `curl -XDELETE localhost:9200/index_a?pretty`
    - psm: `delete > localhost:9200/index_b` 
- 创建文档：类型和文档需要一起创建，重复创建视为修改操作：
    - cmd: `curl -XPUT localhost:9200/index_a/user/1?pretty`：
        - 增加 `-H "content-type: application/json"` 设定请求数据的MIME类型，必须使用双引号。
        - 增加 `-d "{\"age\": 18}"` 设定请求数据内容，json数据内外皆必须使用双引号。
        - 索引 `index_a` 和类型 `user` 均可以自动创建，`1` 表示当前文档ID。
    - psm: `put > localhost:9200/index_a/user/2`
        - 在请求体中选择 `raw` 并添加json数据，key值必须使用双引号，MIME类型选择json。
- 查看文档：分别查询索引 `index_a` 中id为1和2的文档数据：
    - cmd: `curl -XGET localhost:9200/index_a/user/1?pretty`
    - psm: `get > localhost:9200/index_a/user/2`
- 删除文档：删除索引 `index_a` 中id为1和2的文档数据：
    - cmd: `curl -XDELETE localhost:9200/index_a/user/1?pretty`
    - psm: `delete > localhost:9200/index_a/user/2`

# 3. REST高级查询

**概念：** 向 `index_a` 中添加如下数据，以便测试高级查询： 
- psm: `put > localhost:9200/index_a/user/1~5`：
    - `{"id": 1, "name": "zhao si", "gender": "male", "age": 58, "info": "亚洲四小龙 亚洲舞王"}`
    - `{"id": 2, "name": "liu neng", "gender": "male", "age": 58, "info": "亚洲四小龙 村副主任"}`
    - `{"id": 3, "name": "xie da jiao", "gender": "female", "age": 18, "info": "大脚超市市长 大众情人"}`
    - `{"id": 4, "name": "xie guang kun", "gender": "male", "age": 60, "info": "亚洲四小龙 最强老公公"}`
    - `{"id": 5, "name": "xie lan", "gender": "female", "age": 60, "info": "皮常山老婆"}`

## 3.1 全文检索

**概念：** 全文检索会查询索引中所有文档数据，一次查询默认返回10条文档数据：
- psm: `get > localhost:9200/index_a/user/_search`：
    - `took`：本次检索耗时，单位毫秒。
    - `timed_out`：本次检索是否超时。
    - `_shards`：本次检索的分片情况： 
    - `_shards/total`：一共检索了多少个分片。
    - `_shards/successful`：成功了多少个分片。
    - `_shards/skipped`：跳过了多少个分片。
    - `_shards/failed`：失败了多少个分片。
    - `hits`：本次检索命中情况：
    - `hits/total`：整体命中情况：
    - `hits/total/value`：整体命中了多少个文档。
    - `hits/total/relation`：命中关系，`eq` 表示相等。
    - `hits/max_score`：命中匹配度最高分，值越大表示匹配度越高。
    - `hits/hits`：具体命中的文档数组。

## 3.2 条件查询

**概念：** 条件查询默认对条件字符串按空格拆分，并依次用拆分后的每一部分进行包含检索：
- 使用查询串：在url后附加 `?q=属性名:属性值` 格式的条件，简单但局限性大：
    - `get > localhost:9200/index_a/user/_search?q=name:xie da`
- 使用DSL（领域特定语言）条件：在请求体JSON数据中使用 `query` 属性设置条件：
    - `"query": {"match": {"name": "xie da"}}`：检索name属性中包含 `xie` 或 `da` 的所有文档。
    - `"query": {"match_phrase": {"name": "xie da"}}`：检索name属性中包含 `xie da` 短语的所有文档。
    - `"query": {"match_all": {}}`：检索所有文档。
- 使用DSL排序：在请求体JSON数据中使用 `sort` 属性对结果集进行排序：
    - `"sort": [{"_score": "desc"}, {"age": "asc"}]`：对结果集按匹配分数降序，分数相同再按年龄升序：
    - ES默认使用分数降序，使用其他字段排序则score为null，若想同时生效则需对score显示设置排序。
    - 尽量只使用数字进行排序，如果非要对字符串排序，用 `name.keyword` 替换 `name`。
- 使用DSL分页：在请求体JSON数据中使用 `from/size` 属性对结果集进行分页：
    - `"from": 0`：从第0条文档开始检索。
    - `"size": 3`：总计检索3条文档。

## 3.3  文档高亮查询

**概念：** 在ES中检索出高亮片段很容易，只需要添加一个 `highlight` 值并且指定高亮内容即可，高亮的效果可以使用 `pre_tags` 和 `post_tags` 自定义，如果不指定这两个属性，则内容最以 `<em></em>` 进行修饰。

**数据：** `GET localhost:9200/my-index/_doc/_search`
```json 
{
    "query" : {
        "match_phrase" : {
            "name" : "xie da"
        }
    },
    "highlight": {
        "pre_tags": "<mark>",
        "post_tags": "</mark>", 
        "fields" : {
            "name" : {}
        }
    }
}
```

# 5.  bool组合条件查询

**概念：** 更复杂的条件可以使用 `bool`（布尔组合）进行组合。

**需求：** 寻找名字中的带zhao，年龄在18-60岁之间，但id不是1也不是2的文档。

**数据：** `GET localhost:9200/my-index/_doc/_search`
```json
{
    "query": {
        "bool": {
            "must": {"match": {"name": "zhao"}},
            "must_not": [
                {"match": { "id": 1}},
                {"match": { "id": 2}}
            ],
            "filter": {
                "range": {
                    "age": {
                        "gte": 18,
                        "lte": 60
                    }
                }
            }
        }
    }
}
```

# 6. 聚合

**概念：** 
- 聚合查询可以使用属性 `aggs` 配合 `group_by_xxx`，`xxx` 需要改成即将参与聚合的属性。
- `group_by_xxx` 中使用 `terms` 进行分组，其中的 `field` 需要指定具体聚合属性名。
    - 如果参与聚合的属性为数字类型，直接使用。
    - 如果参与聚合的属性是字符串类型，需要添加.keyword后缀，如将 `name` 改为 `name.keyword`。
- 聚合分组后除了聚合信息后，还会展示参与聚合的所有文档的信息，如果你不想看到他们，可以使用 `"size": 0` 将其过滤掉。

**需求：** 按照姓名分组后，查询每个姓名的平均年龄。

**数据：** `GET localhost:9200/my-index/_doc/_search`
```json
{
  "size": 0,
  "aggs": {
    "group_by_gender": {
      "terms": {
        "field": "gender.keyword"
      },
      "aggs": {
        "average_age": {
          "avg": {
            "field": "age"
          }
        }
      }
    }
  }
}
```







