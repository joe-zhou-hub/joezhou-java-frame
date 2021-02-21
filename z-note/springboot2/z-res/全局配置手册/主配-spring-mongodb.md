**配置：**
```properties
# EMBEDDED MONGODB (EmbeddedMongoProperties)

# Comma-separated list of features to enable.
spring.mongodb.embedded.features=SYNC_DELAY 

# Directory used for data storage.
spring.mongodb.embedded.storage.database-dir= 

# Maximum size of the oplog in megabytes.
spring.mongodb.embedded.storage.oplog-size= 

# Name of the replica set.
spring.mongodb.embedded.storage.repl-set-name= 

# Version of Mongo to use.
spring.mongodb.embedded.version=2.6.10 

# MONGODB (MongoProperties)

# Authentication database name.
spring.data.mongodb.authentication-database= 

# Database name.
spring.data.mongodb.database=test 

# Fully qualified name of the FieldNamingStrategy to use.
spring.data.mongodb.field-naming-strategy= 

# GridFS database name.
spring.data.mongodb.grid-fs-database= 

# Mongo server host. Cannot be set with uri.
spring.data.mongodb.host=localhost 

# Login password of the mongo server. Cannot be set with uri.
spring.data.mongodb.password= 

# Mongo server port. Cannot be set with uri.
spring.data.mongodb.port=27017 

# Enable Mongo repositories.
spring.data.mongodb.repositories.enabled=true 

# Mongo database URI. Cannot be set with host, port and credentials.
spring.data.mongodb.uri=mongodb://localhost/test 

# Login user of the mongo server. Cannot be set with uri.
spring.data.mongodb.username= 
```