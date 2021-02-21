**配置：** DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
```properties
# Do not stop if an error occurs while initializing the database.
spring.datasource.continue-on-error=false 

# Data (DML) script resource references.
spring.datasource.data= 

# User of the database to execute DML scripts (if different).
spring.datasource.data-username= 

# Password of the database to execute DML scripts (if different).
spring.datasource.data-password= 

# Commons DBCP2 specific settings
spring.datasource.dbcp2.*= 

# Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
spring.datasource.driver-class-name= 

# Generate a random datasource name.
spring.datasource.generate-unique-name=false 

# Hikari specific settings
spring.datasource.hikari.*= 

# Populate the database using 'data.sql'.
spring.datasource.initialize=true 

# Enable JMX support (if provided by the underlying pool).
spring.datasource.jmx-enabled=false 

# JNDI location of the datasource. Class, url, username & password are ignored when set.
spring.datasource.jndi-name= 

# Name of the datasource.
spring.datasource.name=testdb 

# Login password of the database.
spring.datasource.password= 

# Platform to use in the schema resource (schema-${platform}.sql).
spring.datasource.platform=all 

# Schema (DDL) script resource references.
spring.datasource.schema= 

# User of the database to execute DDL scripts (if different).
spring.datasource.schema-username= 

# Password of the database to execute DDL scripts (if different).
spring.datasource.schema-password= 

# Statement separator in SQL initialization scripts.
spring.datasource.separator=; 

# SQL scripts encoding.
spring.datasource.sql-script-encoding= 

# Tomcat datasource specific settings
spring.datasource.tomcat.*= 

# Fully qualified name of the connection pool implementation to use. 
# By default, it is auto-detected from the classpath.
spring.datasource.type= 

# JDBC url of the database.
spring.datasource.url= 

# JDBC username of the database.
spring.datasource.username=
```