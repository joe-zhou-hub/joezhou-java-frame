# springboot整合JSP

**概念：** jsp和thymeleaf默认情况下无法共存，需要一些额外的配置，所以我们在测试jsp的时候，需要删除thymeleaf的相关配置。
- 由于SpringBoot使用的内嵌的tomcat，而内嵌的tamcat是不支持jsp页面的，所有需要导入额外的包才能解决。
- 我和springBoot都并不建议使用jsp，如果真有需要知道jsp和thymeleaf共存的额外配置，还请自行查找。

```xml
<!--tomcat-embed-jasper-->
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <scope>provided</scope>
</dependency>
```