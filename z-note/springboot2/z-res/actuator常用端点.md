端点             | 描述
:---:|:---:
`health`         | 展示项目中的健康信息，默认暴露
`info`           | 展示任意的项目信息，默认暴露
`metrics`        | 展示运行状态的度量信息
`auditevents`    | 展示当前项目中的审计事件信息，需要 `AuditEventRepository` 的bean
`beans`          | 展示当前项目中所有Spring bean的完整列表
`caches`         | 展示项目中可用缓存列表
`scheduledtasks` | 展示项目中的定时任务列表
`loggers`        | 展示和修改项目中的日志
`mappings`       | 展示 `@RequestMapping` 路径列表
`configprops`    | 展示 `@ConfigurationProperties` 的路径列表
`env`            | 展示所有来自于 `ConfigurableEnvironment` 的属性列表
`conditions`     | 展示在配置和自动配置类上评估的条件，以及它们匹配或不匹配的原因
`httptrace`      | 展示HTTP跟踪信息(默认情况下，最后100个HTTP请求-响应交换)，需要 `HttpTraceRepository` 的bean
`liquibase`      | 展示 `Liquibase` 数据库迁移信息
`flyway`         | 展示 `Flyway` 数据库迁移信息，需要一个或多个 `Flyway` 的bean
`sessions`       | 允许操作session
`shutdown`       | 允许以优雅的方式关闭，默认禁用，需要使用POST请求
`threaddump`     | 执行线程转储