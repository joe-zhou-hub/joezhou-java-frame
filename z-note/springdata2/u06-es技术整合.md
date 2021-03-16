# 1. springboot整合es

**概念：** 新建springboot项目 `springdata2-es`：
- 添加依赖：`lombok/spring-boot-starter-data-elasticsearch`
- 在主配中指定ES服务器或集群：
    - `spring.elasticsearch.rest.uris: http://localhost:9200`
- 开发实体类 `entity.User`： 
    - `@Document`：一个实体类就是一个文档，使用 `indexName` 属性来指定索引名，如果ES中没有这个索引，会自动创建。
    - `@Id`：用来标识一个主键字段，非必须。
    - `@Field`：用来标识一个普通字段，非必须。
- 开发空接口 `service.UserRepository`
    - 接口上标记 `@Repository` 使其被spring容器管理。
    - 继承 `ElasticsearchRepository<文档类型, 主键类型>`，以便使用ES提供的的API。
- 测试：
    - psm: `localhost:9200/test-index/_doc/1`
        - 使用默认的type名称 `_doc` 进行测试。
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

# 4. ElasticsearchRepository

**概念：** 
- `ElasticsearchRepository` 提供了一些API，但大部分均已过时。
- `UserRepository` 继承了 `ElasticsearchRepository`，是我们自定义的ES搜索API库。
- 我们可以在 `UserRepository` 中使用某些关键字来自定义API方法：
    - 支持的关键字有：`Like` / `Not` / `And` / `Or` / `Between` / `OrderBy` 等，可以组合使用。
    - 方法格式示例：返回值均为 `List<User>`
    - `findByName(String name)`：检索姓名为XXX的所有文档。
    - `findOrderByName(String name)`：按照姓名倒序排序所有文档。
    - `findByNameLike(String name)`：模糊检索姓名为XXX的所有文档。
    - `findByNameNot(String name)`：检索姓名不是XXX的所有文档。
    - `findByNameStartingWith(String prefix)`：检索姓名以XXX开头的所有文档。
    - `findByNameEndingWith(String suffix)`：检索姓名以XXX结尾的所有文档。
    - `findByNameContaining(String content)`：检索姓名总包含XXX的所有文档。
    - `findByNameIn(List names)`：检索姓名在列表XXX中的所有文档。
    - `findByNameNotIn(List names)`：检索姓名不在列表XXX中的所有文档。
    - `findByGenderTrue(boolean gender)`：检索性别为true的所有文档。
    - `findByGenderFalse(boolean false)`：检索性别为false的所有文档。
    - `findByNameAndAbout(String name, String about)`：根据 `name` 和 `about` 查询同时满足条件的文档。
    - `findByNameOrAbout(String name, String about)`：根据 `name` 和 `about` 查询任一满足条件的文档。
    - `findByIdBetween(Integer min, Integer max)`：检索id在XXX和XXX之间的所有文档。
    - `findByIdLessThan(Integer num)`：检索id在小于XXX的所有文档。
    - `findByIdGreaterThan(Integer num)`：检索id在大于XXX的所有文档。
    - 等等。
    
> Idea中接口方法会有提示。

**源码：** UserRepository.java 中添加
```java
/**
 * 按照姓名查找全部文档
 * @param name 姓名
 * @return 满足条件的文档列表
 */
List<User> findByName(String name);
```

**源码：** ElastciSearchTest.java 中添加
```java
@Test
public void findByName() {
    List<User> users = userRepository.findByName("批增姓名01");
    users.forEach(System.out::println);
}
```