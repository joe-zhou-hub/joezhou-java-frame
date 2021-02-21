**配置：**
```properties
# HEALTH INDICATORS
management.health.db.enabled=true # Enable database health check.
management.health.cassandra.enabled=true # Enable cassandra health check.
management.health.couchbase.enabled=true # Enable couchbase health check.
management.health.defaults.enabled=true # Enable default health indicators.
management.health.diskspace.enabled=true # Enable disk space health check.
management.health.diskspace.path= # Path used to compute the available disk space.
management.health.diskspace.threshold=0 # Minimum disk space that should be available, in bytes.
management.health.elasticsearch.enabled=true # Enable elasticsearch health check.
management.health.elasticsearch.indices= # Comma-separated index names.
management.health.elasticsearch.response-timeout=100 
# The time, in milliseconds, to wait for a response from the cluster.
management.health.jms.enabled=true # Enable JMS health check.
management.health.ldap.enabled=true # Enable LDAP health check.
management.health.mail.enabled=true # Enable Mail health check.
management.health.mongo.enabled=true # Enable MongoDB health check.
management.health.rabbit.enabled=true # Enable RabbitMQ health check.
management.health.redis.enabled=true # Enable Redis health check.
management.health.solr.enabled=true # Enable Solr health check.
management.health.status.order=DOWN, OUT_OF_SERVICE, UP, UNKNOWN 
# Comma-separated list of health statuses in order of severity.

# MANAGEMENT HTTP SERVER (ManagementServerProperties)
management.add-application-context-header=true 
# Add the "X-Application-Context" HTTP header in each response.
management.address= # Network address that the management endpoints should bind to.
management.context-path= # Management endpoint context-path. For instance `/actuator`
management.cloudfoundry.enabled= # Enable extended Cloud Foundry actuator endpoints
management.cloudfoundry.skip-ssl-validation= 
# Skip SSL verification for Cloud Foundry actuator endpoint security calls
management.port= 
# Management endpoint HTTP port. Uses the same port as the application by default. 
# Configure a different port to use management-specific SSL.
management.security.enabled=true # Enable security.
management.security.roles=ACTUATOR # Comma-separated list of roles that can access the management endpoint.
management.security.sessions=stateless 
# Session creating policy to use (always, never, if_required, stateless).
management.ssl.ciphers= # Supported SSL ciphers. Requires a custom management.port.
management.ssl.client-auth= 
# Whether client authentication is wanted ("want") or needed ("need"). Requires a trust store. 
# Requires a custom management.port.
management.ssl.enabled= # Enable SSL support. Requires a custom management.port.
management.ssl.enabled-protocols= # Enabled SSL protocols. Requires a custom management.port.
management.ssl.key-alias= 
# Alias that identifies the key in the key store. Requires a custom management.port.
management.ssl.key-password= 
# Password used to access the key in the key store. Requires a custom management.port.
management.ssl.key-store= 
# Path to the key store that holds the SSL certificate (typically a jks file). 
# Requires a custom management.port.
management.ssl.key-store-password= 
# Password used to access the key store. Requires a custom management.port.
management.ssl.key-store-provider= # Provider for the key store. Requires a custom management.port.
management.ssl.key-store-type= # Type of the key store. Requires a custom management.port.
management.ssl.protocol=TLS # SSL protocol to use. Requires a custom management.port.
management.ssl.trust-store= # Trust store that holds SSL certificates. Requires a custom management.port.
management.ssl.trust-store-password= 
# Password used to access the trust store. Requires a custom management.port.
management.ssl.trust-store-provider= # Provider for the trust store. Requires a custom management.port.
management.ssl.trust-store-type= # Type of the trust store. Requires a custom management.port.

# TRACING (TraceProperties)
management.trace.include=request-headers,response-headers,cookies,errors 
# Items to be included in the trace.

# REMOTE SHELL (ShellProperties)
management.shell.auth.type=simple # Authentication type. Auto-detected according to the environment.
management.shell.auth.jaas.domain=my-domain # JAAS domain.
management.shell.auth.key.path= # Path to the authentication key. This should point to a valid ".pem" file.
management.shell.auth.simple.user.name=user # Login user.
management.shell.auth.simple.user.password= # Login password.
management.shell.auth.spring.roles=ACTUATOR 
# Comma-separated list of required roles to login to the CRaSH console.
management.shell.command-path-patterns=classpath*:/commands/**,classpath*:/crash/commands/** 
# Patterns to use to look for commands.
management.shell.command-refresh-interval=-1 
# Scan for changes and update the command if necessary (in seconds).
management.shell.config-path-patterns=classpath*:/crash/* # Patterns to use to look for configurations.
management.shell.disabled-commands=jpa*,jdbc*,jndi* # Comma-separated list of commands to disable.
management.shell.disabled-plugins= 
# Comma-separated list of plugins to disable. 
# Certain plugins are disabled by default based on the environment.
management.shell.ssh.auth-timeout = # Number of milliseconds after user will be prompted to login again.
management.shell.ssh.enabled=true # Enable CRaSH SSH support.
management.shell.ssh.idle-timeout = # Number of milliseconds after which unused connections are closed.
management.shell.ssh.key-path= # Path to the SSH server key.
management.shell.ssh.port=2000 # SSH port.
management.shell.telnet.enabled=false 
# Enable CRaSH telnet support. Enabled by default if the TelnetPlugin is  available.
management.shell.telnet.port=5000 # Telnet port.
```