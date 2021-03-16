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


> 测试接口：GET localhost:9200/test-index/_doc/1

> 首先完成 [U04_ES7整合SB2-基本配通]()。

# crud

## 1. Repository

**概念：** `Repository` 是中央存储库标记接口，也是ES操作接口的最上层接口。

**继承关系：**
```txt
|_ Repository：中央存储库标记接口
    |_ CrudRepository：CRUD操作接口
        |_ PagingAndSortingRepository：分页和排序操作接口
            |_ ElasticsearchRepository：ES仓库接口
                |_ UserRepository：用户自定义接口
```

# 2. CrudRepository

**概念：** `CrudRepository` 提供了一些常用的增删改查API接口：
- `<S extends T> S save(S entity);`：索引单条文档
- `<S extends T> Iterable<S> saveAll(Iterable<S> entities);`：索引多条文档
- `Optional<T> findById(ID id);`：通过ID检索指定文档
- `boolean existsById(ID id);`：通过ID确认指定文档是否存在
- `Iterable<T> findAll();`：检索全部文档
- `Iterable<T> findAllById(Iterable<ID> ids);`：通过ID列表检索多条指定文档
- `long count();`：检索文档数量
- `void deleteById(ID id);`：通过ID删除指定文档
- `void delete(T entity);`：通过文档对象删除指定文档
- `void deleteAll(Iterable<? extends T> entities);`：通过文档对象列表删除指定文档
- `void deleteAll();`：删除所有文档

## 2.1 save / saveAll

**源码：** ElastciSearchTest.java 中添加
```java
@Test
void save() {
    User user = new User(1, "测试姓名", "描述信息");
    userRepository.save(user);
}
    
@Test
void saveAll() {
    List<User> users = new ArrayList<>();
    User user01 = new User(2, "批增姓名02", "批增信息02");
    User user02 = new User(3, "批增姓名03", "批增信息03");
    User user03 = new User(4, "批增姓名04", "批增信息04");
    User user04 = new User(5, "批增姓名05", "批增信息05");
    users.add(user01);
    users.add(user02);
    users.add(user03);
    users.add(user04);
    userRepository.saveAll(users);
}
```

> 同ID文档多次重复添加视为修改操作。

## 2.2 findById / existsById

**源码：** ElastciSearchTest.java 中添加
```java
@Test
void findById(){
    User user = null;
    Optional<User> userOp = userRepository.findById(1);
    if(userOp.isPresent()){
        user = userOp.get();
    }
    System.out.println(user);
}

@Test
void existsById(){
    System.out.println(userRepository.existsById(1));
}
```

> `Optional` 是JDK1.8新出的类，允许包含null值，它包含了很多处理null值的办法，可以利用 `isPresent()` 来优雅的判断一个变量是否为空，避免粗暴的空指针异常。

## 2.3 findAll / findAllById / count

**源码：** ElastciSearchTest.java 中添加
```java
@Test
public void findAll() {
    Iterable<User> usersIterable = userRepository.findAll();
    usersIterable.forEach(System.out::println);
}

@Test
public void findAllById() {
    List<Integer> ids = new ArrayList<>();
    ids.add(1);
    ids.add(2);
    Iterable<User> usersIterable = userRepository.findAllById(ids);
    usersIterable.forEach(System.out::println);
}

@Test
public void count() {
    System.out.println(userRepository.count());
}
```

## 2.4 deleteById / delete / deleteAll

**源码：** ElastciSearchTest.java 中添加
```java
@Test
public void deleteById() {
    userRepository.deleteById(1);
}

@Test
public void delete() {
    User user = new User();
    // 必须填写ID，其余字段可以不写
    user.setId(3);
    userRepository.delete(user);
}

@Test
public void deleteAll() {
    List<User> users = new ArrayList<>();
    User user11 = new User(11, "", "");
    User user12 = new User(12, "", "");
    users.add(user11);
    users.add(user12);

    // 参数需要填写id不为空的实体列表，不填表示全删
    userRepository.deleteAll(users);
}
```

# 3. PagingAndSortingRepository

**概念：** 提供了排序和分页查询的两个接口方法：
- `Iterable<T> findAll(Sort sort);`：注入Sort对象，对查询结果排序。
- `Page<T> findAll(Pageable pageable);`：注入Pageable对象，对查询结果分页。

## 3.1 查询排序

**源码：** ElastciSearchTest.java 中添加
```java
@Test
public void findSortedAll() {
    Sort descSortById = Sort.by("id").descending();
    Iterable<User> usersIterable = userRepository.findAll(descSortById);
    // 快速forEach遍历
    usersIterable.forEach(System.out::println);
}
```

## 3.2 查询分页

**源码：** ElastciSearchTest.java 中添加
```java
@Test
public void findBySort() {
    Sort sort = Sort.by("id").descending();
    Iterable<User> usersIterable = userRepository.findAll(sort);
    usersIterable.forEach(System.out::println);
}

@Test
void findByPage() {
    // p3可以省略，省略默认不排序
    Pageable page = PageRequest.of(0, 3, Sort.by("id"));
    if (page.isPaged()) {
        Page<User> users = userRepository.findAll(page);
        System.out.println("当前页数：" + page.getPageNumber());
        System.out.println("页面尺寸：" + page.getPageSize());
        System.out.println("总条目数：" + users.getTotalElements());
        System.out.println("总页数：" + users.getTotalPages());
        users.forEach(System.out::println);
    }
}
```

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