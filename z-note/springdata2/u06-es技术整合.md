# 1. springboot整合es

**概念：** 新建springboot项目 `springdata2-elasticsearch`：
- 添加依赖：`lombok/spring-boot-starter-data-elasticsearch`
- 在主配中指定ES服务器或集群：
    - `spring.elasticsearch.rest.uris: http://localhost:9200`
- 开发实体类 `entity.User`： 
    - `@Document`：一个实体类就是一个文档，使用 `indexName` 属性来指定索引名，如果ES中没有这个索引，会自动创建。
    - `@Id`：用来标识一个主键字段，非必须。
    - `@Field`：用来标识一个普通字段，非必须。
- 开发空接口 `service.UserRepository`
    - 接口上标记 `@Repository` 使其被spring容器管理。
    - 继承 `ElasticsearchRepository<文档类型, 主键类型>`，以用其API。
- 开发测试类 `es.ElasticsearchRepositoryTest`：
    - `<S extends T> S save(S entity);`：存储单条文档。
    - `<S extends T> Iterable<S> saveAll(Iterable<S> entities);`：存储多条文档。
    - `Optional<T> findById(ID id);`：通过ID检索指定文档。
    - `boolean existsById(ID id);`：通过ID确认指定文档是否存在。
    - `Iterable<T> findAll();`：检索全部文档。
    - `Iterable<T> findAllById(Iterable<ID> ids);`：通过ID列表检索多条指定文档。
    - `long count();`：检索文档数量。
    - `void deleteById(ID id);`：通过ID删除指定文档。
    - `void delete(T entity);`：通过文档对象删除指定文档。
    - `void deleteAll(Iterable<? extends T> entities);`：通过文档对象列表删除指定文档。
    - `void deleteAll();`：删除所有文档。