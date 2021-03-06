**配置：** JPA (JpaBaseConfiguration, HibernateJpaAutoConfiguration)
```properties
# Enable JPA repositories.
spring.data.jpa.repositories.enabled=true 

# Target database to operate on, auto-detected by default. 
# Can be alternatively set using the "databasePlatform" property.
spring.jpa.database= 

# Name of the target database to operate on, auto-detected by default. 
# Can be alternatively set using the "Database" enum.
spring.jpa.database-platform= 

# Initialize the schema on startup.
spring.jpa.generate-ddl=false 

# DDL mode. This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. 
# Default to "create-drop" when using an embedded database, "none" otherwise.
spring.jpa.hibernate.ddl-auto= 

# Hibernate 5 implicit naming strategy fully qualified name.
spring.jpa.hibernate.naming.implicit-strategy= 

# Hibernate 5 physical naming strategy fully qualified name.
spring.jpa.hibernate.naming.physical-strategy= 

# Hibernate 4 naming strategy fully qualified name. Not supported with Hibernate 5.
spring.jpa.hibernate.naming.strategy= 

# Use Hibernate's newer IdentifierGenerator for AUTO, TABLE and SEQUENCE.
spring.jpa.hibernate.use-new-id-generator-mappings= 

# Register OpenEntityManagerInViewInterceptor. 
# Binds a JPA EntityManager to the thread for the entire processing of the request.
spring.jpa.open-in-view=true 

# Additional native properties to set on the JPA provider.
spring.jpa.properties.*= 

# Enable logging of SQL statements.
spring.jpa.show-sql=false 
```