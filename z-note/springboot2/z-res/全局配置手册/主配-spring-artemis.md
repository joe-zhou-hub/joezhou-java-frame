**配置：** ARTEMIS (ArtemisProperties)
```properties
# Cluster password. Randomly generated on startup by default.
spring.artemis.embedded.cluster-password= 

# Journal file directory. Not necessary if persistence is turned off.
spring.artemis.embedded.data-directory= 

# Enable embedded mode if the Artemis server APIs are available.
spring.artemis.embedded.enabled=true 

# Enable persistent store.
spring.artemis.embedded.persistent=false 

# Comma-separated list of queues to create on startup.
spring.artemis.embedded.queues= 

# Server id. By default, an auto-incremented counter is used.
spring.artemis.embedded.server-id= 

# Comma-separated list of topics to create on startup.
spring.artemis.embedded.topics= 

# Artemis broker host.
spring.artemis.host=localhost 

# Artemis deployment mode, auto-detected by default.
spring.artemis.mode= 

# Login password of the broker.
spring.artemis.password= 

# Artemis broker port.
spring.artemis.port=61616 

# Login user of the broker.
spring.artemis.user= 
```