**配置：** FLYWAY (FlywayProperties)
```properties
flyway.baseline-description= 

# version to start migration
flyway.baseline-version=1 

flyway.baseline-on-migrate= 

# Check that migration scripts location exists.
flyway.check-location=false 

flyway.clean-on-validation-error=

# Enable flyway.
flyway.enabled=true 

flyway.encoding= 

flyway.ignore-failed-future-migration= 

# SQL statements to execute to initialize a connection immediately after obtaining it.
flyway.init-sqls= 

# locations of migrations scripts
flyway.locations=classpath:db/migration 

flyway.out-of-order=

# JDBC password if you want Flyway to create its own DataSource
flyway.password=

flyway.placeholder-prefix= 

flyway.placeholder-replacement= 

flyway.placeholder-suffix= 

flyway.placeholders.*= 

# schemas to update
flyway.schemas= 

flyway.sql-migration-prefix=V 

flyway.sql-migration-separator= 

flyway.sql-migration-suffix=.sql 

flyway.table= 

# JDBC url of the database to migrate. If not set, the primary configured data source is used.
flyway.url= 

# Login user of the database to migrate.
flyway.user= 

flyway.validate-on-migrate=
```