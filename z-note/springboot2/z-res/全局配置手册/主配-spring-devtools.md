# spring.devtools

```properties
# Context path used to handle the remote connection.
spring.devtools.remote.context-path=/.~~spring-boot!~ 

# Enable remote debug support.
spring.devtools.remote.debug.enabled=true 

# Local remote debug server port.
spring.devtools.remote.debug.local-port=8000 

# The host of the proxy to use to connect to the remote application.
spring.devtools.remote.proxy.host= 

# The port of the proxy to use to connect to the remote application.
spring.devtools.remote.proxy.port= 

# Enable remote restart.
spring.devtools.remote.restart.enabled=true 

# A shared secret required to establish a connection (required to enable remote support).
spring.devtools.remote.secret= 

# HTTP header used to transfer the shared secret.
spring.devtools.remote.secret-header-name=X-AUTH-TOKEN 

# Enable a livereload.com compatible server.
spring.devtools.livereload.enabled=true 

# Server port.
spring.devtools.livereload.port=35729 

# Additional patterns that should be excluded from triggering a full restart.
spring.devtools.restart.additional-exclude= 

# Additional paths to watch for changes.
spring.devtools.restart.additional-paths= 

# Enable automatic restart.
spring.devtools.restart.enabled=true 

# Patterns that should be excluded from triggering a full restart.
spring.devtools.restart.exclude=
\META-INF/maven/**,
\META-INF/resources/**,
\resources/**,
\static/**,
\public/**,
\templates/**,
\**/*Test.class,
\**/*Tests.class,
\git.properties 

# Amount of time (in milliseconds) to wait between polling for classpath changes.
spring.devtools.restart.poll-interval=1000 

# Amount of quiet time (in milliseconds) required without any classpath changes 
# before a restart is triggered.
spring.devtools.restart.quiet-period=400 

# Name of a specific file that when changed will trigger the restart check. 
# If not specified any classpath file change will trigger the restart.
spring.devtools.restart.trigger-file= 
```