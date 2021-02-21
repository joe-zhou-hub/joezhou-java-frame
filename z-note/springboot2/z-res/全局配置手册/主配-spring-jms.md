**配置：** JMS (JmsProperties)
```properties
# Connection factory JNDI name. When set, 
# takes precedence to others connection factory auto-configurations.
spring.jms.jndi-name= 

# Acknowledge mode of the container. By default, the listener is transacted with automatic acknowledgment.
spring.jms.listener.acknowledge-mode= 

# Start the container automatically on startup.
spring.jms.listener.auto-startup=true 

# Minimum number of concurrent consumers.
spring.jms.listener.concurrency= 

# Maximum number of concurrent consumers.
spring.jms.listener.max-concurrency= 

# Specify if the default destination type is topic.
spring.jms.pub-sub-domain=false 

# Default destination to use on send/receive operations that do not have a destination parameter.
spring.jms.template.default-destination= 

# Delivery delay to use for send calls in milliseconds.
spring.jms.template.delivery-delay= 

# Delivery mode. Enable QoS when set.
spring.jms.template.delivery-mode= 

# Priority of a message when sending. Enable QoS when set.
spring.jms.template.priority= 

# Enable explicit QoS when sending a message.
spring.jms.template.qos-enabled= 

# Timeout to use for receive calls in milliseconds.
spring.jms.template.receive-timeout= 

# Time-to-live of a message when sending in milliseconds. Enable QoS when set.
spring.jms.template.time-to-live= 
```