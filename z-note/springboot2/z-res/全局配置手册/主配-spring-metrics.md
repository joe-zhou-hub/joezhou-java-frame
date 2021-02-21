**配置：**
```properties
# METRICS EXPORT (MetricExportProperties)
spring.metrics.export.aggregate.key-pattern= 
# Pattern that tells the aggregator what to do with the keys from the source repository.
spring.metrics.export.aggregate.prefix= # Prefix for global repository if active.
spring.metrics.export.delay-millis=5000 
# Delay in milliseconds between export ticks. 
# Metrics are exported to external sources on a schedule with this delay.
spring.metrics.export.enabled=true # Flag to enable metric export (assuming a MetricWriter is available).
spring.metrics.export.excludes= 
# List of patterns for metric names to exclude. Applied after the includes.
spring.metrics.export.includes= # List of patterns for metric names to include.
spring.metrics.export.redis.key=keys.spring.metrics # Key for redis repository export (if active).
spring.metrics.export.redis.prefix=spring.metrics # Prefix for redis repository if active.
spring.metrics.export.send-latest= 
# Flag to switch off any available optimizations based on not exporting unchanged metric values.
spring.metrics.export.statsd.host= # Host of a statsd server to receive exported metrics.
spring.metrics.export.statsd.port=8125 # Port of a statsd server to receive exported metrics.
spring.metrics.export.statsd.prefix= # Prefix for statsd exported metrics.
spring.metrics.export.triggers.*= # Specific trigger properties per MetricWriter bean name.
```