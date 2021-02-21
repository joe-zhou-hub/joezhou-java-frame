# DAO (PersistenceExceptionTranslationAutoConfiguration)
spring.dao.exceptiontranslation.enabled=true # Enable the PersistenceExceptionTranslationPostProcessor.
 
# JOOQ (JooqAutoConfiguration)
spring.jooq.sql-dialect= 
# SQLDialect JOOQ used when communicating with the configured datasource. For instance `POSTGRES`
 
# JOLOKIA (JolokiaProperties)
jolokia.config.*= # See Jolokia manual

# DATA LDAP
# Enable LDAP repositories.
spring.data.ldap.repositories.enabled=true 
 
# DATA REDIS
# Enable Redis repositories.
spring.data.redis.repositories.enabled=true 