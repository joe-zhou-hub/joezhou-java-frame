**配置：** SPRING BATCH (BatchProperties)
```properties
# Create the required batch tables on startup if necessary. 
# Enabled automatically if no custom table prefix is set or if a custom schema is configured.
spring.batch.initializer.enabled= 

# Execute all Spring Batch jobs in the context on startup.
spring.batch.job.enabled=true 

# Comma-separated list of job names to execute on startup (For instance `job1,job2`). 
# By default, all Jobs found in the context are executed.
spring.batch.job.names= 

# Path to the SQL file to use to initialize the database schema.
spring.batch.schema=classpath:org/springframework/batch/core/schema-@@platform@@.sql 

# Table prefix for all the batch meta-data tables.
spring.batch.table-prefix= 
```