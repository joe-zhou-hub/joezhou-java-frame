**配置：**
```properties
# JTA (JtaAutoConfiguration)
spring.jta.enabled=true # Enable JTA support.
spring.jta.log-dir= # Transaction logs directory.
spring.jta.transaction-manager-id= # Transaction manager unique identifier.
 
# ATOMIKOS (AtomikosProperties)
spring.jta.atomikos.connectionfactory.borrow-connection-timeout=30 
# Timeout, in seconds, for borrowing connections from the pool.
spring.jta.atomikos.connectionfactory.ignore-session-transacted-flag=true 
# Whether or not to ignore the transacted flag when creating session.
spring.jta.atomikos.connectionfactory.local-transaction-mode=false 
# Whether or not local transactions are desired.
spring.jta.atomikos.connectionfactory.maintenance-interval=60 
# The time, in seconds, between runs of the pool's maintenance thread.
spring.jta.atomikos.connectionfactory.max-idle-time=60 
# The time, in seconds, after which connections are cleaned up from the pool.
spring.jta.atomikos.connectionfactory.max-lifetime=0 
# The time, in seconds, that a connection can be pooled for before being destroyed. 0 denotes no limit.
spring.jta.atomikos.connectionfactory.max-pool-size=1 # The maximum size of the pool.
spring.jta.atomikos.connectionfactory.min-pool-size=1 # The minimum size of the pool.
spring.jta.atomikos.connectionfactory.reap-timeout=0 
# The reap timeout, in seconds, for borrowed connections. 0 denotes no limit.
spring.jta.atomikos.connectionfactory.unique-resource-name=jmsConnectionFactory 
# The unique name used to identify the resource during recovery.
spring.jta.atomikos.datasource.borrow-connection-timeout=30 
# Timeout, in seconds, for borrowing connections from the pool.
spring.jta.atomikos.datasource.default-isolation-level= 
# Default isolation level of connections provided by the pool.
spring.jta.atomikos.datasource.login-timeout= 
# Timeout, in seconds, for establishing a database connection.
spring.jta.atomikos.datasource.maintenance-interval=60 
# The time, in seconds, between runs of the pool's maintenance thread.
spring.jta.atomikos.datasource.max-idle-time=60 
# The time, in seconds, after which connections are cleaned up from the pool.
spring.jta.atomikos.datasource.max-lifetime=0 
# The time, in seconds, that a connection can be pooled for before being destroyed. 0 denotes no limit.
spring.jta.atomikos.datasource.max-pool-size=1 # The maximum size of the pool.
spring.jta.atomikos.datasource.min-pool-size=1 # The minimum size of the pool.
spring.jta.atomikos.datasource.reap-timeout=0 
# The reap timeout, in seconds, for borrowed connections. 0 denotes no limit.
spring.jta.atomikos.datasource.test-query= 
# SQL query or statement used to validate a connection before returning it.
spring.jta.atomikos.datasource.unique-resource-name=dataSource 
# The unique name used to identify the resource during recovery.
spring.jta.atomikos.properties.checkpoint-interval=500 # Interval between checkpoints.
spring.jta.atomikos.properties.console-file-count=1 # Number of debug logs files that can be created.
spring.jta.atomikos.properties.console-file-limit=-1 
# How many bytes can be stored at most in debug logs files.
spring.jta.atomikos.properties.console-file-name=tm.out # Debug logs file name.
spring.jta.atomikos.properties.console-log-level=warn # Console log level.
spring.jta.atomikos.properties.default-jta-timeout=10000 # Default timeout for JTA transactions.
spring.jta.atomikos.properties.enable-logging=true # Enable disk logging.
spring.jta.atomikos.properties.force-shutdown-on-vm-exit=false 
# Specify if a VM shutdown should trigger forced shutdown of the transaction core.
spring.jta.atomikos.properties.log-base-dir= # Directory in which the log files should be stored.
spring.jta.atomikos.properties.log-base-name=tmlog # Transactions log file base name.
spring.jta.atomikos.properties.max-actives=50 # Maximum number of active transactions.
spring.jta.atomikos.properties.max-timeout=300000 
# Maximum timeout (in milliseconds) that can be allowed for transactions.
spring.jta.atomikos.properties.output-dir= # Directory in which to store the debug log files.
spring.jta.atomikos.properties.serial-jta-transactions=true 
# Specify if sub-transactions should be joined when possible.
spring.jta.atomikos.properties.service= # Transaction manager implementation that should be started.
spring.jta.atomikos.properties.threaded-two-phase-commit=true 
# Use different (and concurrent) threads for two-phase commit on the participating resources.
spring.jta.atomikos.properties.transaction-manager-unique-name= # Transaction manager's unique name.
 
# BITRONIX
spring.jta.bitronix.connectionfactory.acquire-increment=1 
# Number of connections to create when growing the pool.
spring.jta.bitronix.connectionfactory.acquisition-interval=1 
# Time, in seconds, to wait before trying to acquire a connection again after an invalid connection 
# was acquired.
spring.jta.bitronix.connectionfactory.acquisition-timeout=30 
# Timeout, in seconds, for acquiring connections from the pool.
spring.jta.bitronix.connectionfactory.allow-local-transactions=true 
# Whether or not the transaction manager should allow mixing XA and non-XA transactions.
spring.jta.bitronix.connectionfactory.apply-transaction-timeout=false 
# Whether or not the transaction timeout should be set on the XAResource when it is enlisted.
spring.jta.bitronix.connectionfactory.automatic-enlisting-enabled=true 
# Whether or not resources should be enlisted and delisted automatically.
spring.jta.bitronix.connectionfactory.cache-producers-consumers=true 
# Whether or not produces and consumers should be cached.
spring.jta.bitronix.connectionfactory.defer-connection-release=true 
# Whether or not the provider can run many transactions on the same connection 
# and supports transaction interleaving.
spring.jta.bitronix.connectionfactory.ignore-recovery-failures=false 
# Whether or not recovery failures should be ignored.
spring.jta.bitronix.connectionfactory.max-idle-time=60 
# The time, in seconds, after which connections are cleaned up from the pool.
spring.jta.bitronix.connectionfactory.max-pool-size=10 # The maximum size of the pool. 0 denotes no limit.
spring.jta.bitronix.connectionfactory.min-pool-size=0 # The minimum size of the pool.
spring.jta.bitronix.connectionfactory.password= # The password to use to connect to the JMS provider.
spring.jta.bitronix.connectionfactory.share-transaction-connections=false 
#  Whether or not connections in the ACCESSIBLE state can be shared within the context of a transaction.
spring.jta.bitronix.connectionfactory.test-connections=true 
# Whether or not connections should be tested when acquired from the pool.
spring.jta.bitronix.connectionfactory.two-pc-ordering-position=1 
# The position that this resource should take during two-phase commit 
# (always first is Integer.MIN_VALUE, always last is Integer.MAX_VALUE).
spring.jta.bitronix.connectionfactory.unique-name=jmsConnectionFactory 
# The unique name used to identify the resource during recovery.
spring.jta.bitronix.connectionfactory.use-tm-join=
true Whether or not TMJOIN should be used when starting XAResources.
spring.jta.bitronix.connectionfactory.user= # The user to use to connect to the JMS provider.
spring.jta.bitronix.datasource.acquire-increment=1 # Number of connections to create when growing the pool.
spring.jta.bitronix.datasource.acquisition-interval=1 
# Time, in seconds, to wait before trying to acquire a connection again after an invalid connection 
# was acquired.
spring.jta.bitronix.datasource.acquisition-timeout=30 
# Timeout, in seconds, for acquiring connections from the pool.
spring.jta.bitronix.datasource.allow-local-transactions=true 
# Whether or not the transaction manager should allow mixing XA and non-XA transactions.
spring.jta.bitronix.datasource.apply-transaction-timeout=false 
# Whether or not the transaction timeout should be set on the XAResource when it is enlisted.
spring.jta.bitronix.datasource.automatic-enlisting-enabled=true 
# Whether or not resources should be enlisted and delisted automatically.
spring.jta.bitronix.datasource.cursor-holdability= # The default cursor holdability for connections.
spring.jta.bitronix.datasource.defer-connection-release=true 
# Whether or not the database can run many transactions on the same connection 
# and supports transaction interleaving.
spring.jta.bitronix.datasource.enable-jdbc4-connection-test= 
# Whether or not Connection.isValid() is called when acquiring a connection from the pool.
spring.jta.bitronix.datasource.ignore-recovery-failures=false 
# Whether or not recovery failures should be ignored.
spring.jta.bitronix.datasource.isolation-level= # The default isolation level for connections.
spring.jta.bitronix.datasource.local-auto-commit= # The default auto-commit mode for local transactions.
spring.jta.bitronix.datasource.login-timeout= 
# Timeout, in seconds, for establishing a database connection.
spring.jta.bitronix.datasource.max-idle-time=60 
# The time, in seconds, after which connections are cleaned up from the pool.
spring.jta.bitronix.datasource.max-pool-size=10 # The maximum size of the pool. 0 denotes no limit.
spring.jta.bitronix.datasource.min-pool-size=0 # The minimum size of the pool.
spring.jta.bitronix.datasource.prepared-statement-cache-size=0 
# The target size of the prepared statement cache. 0 disables the cache.
spring.jta.bitronix.datasource.share-transaction-connections=false 
#  Whether or not connections in the ACCESSIBLE state can be shared within the context of a transaction.
spring.jta.bitronix.datasource.test-query= 
# SQL query or statement used to validate a connection before returning it.
spring.jta.bitronix.datasource.two-pc-ordering-position=1 
# The position that this resource should take during two-phase 
# commit (always first is Integer.MIN_VALUE, always last is Integer.MAX_VALUE).
spring.jta.bitronix.datasource.unique-name=dataSource 
# The unique name used to identify the resource during recovery.
spring.jta.bitronix.datasource.use-tm-join=
true Whether or not TMJOIN should be used when starting XAResources.
spring.jta.bitronix.properties.allow-multiple-lrc=false 
# Allow multiple LRC resources to be enlisted into the same transaction.
spring.jta.bitronix.properties.asynchronous2-pc=false 
# Enable asynchronously execution of two phase commit.
spring.jta.bitronix.properties.background-recovery-interval-seconds=60 
# Interval in seconds at which to run the recovery process in the background.
spring.jta.bitronix.properties.current-node-only-recovery=true # Recover only the current node.
spring.jta.bitronix.properties.debug-zero-resource-transaction=false 
# Log the creation and commit call stacks of transactions executed without a single enlisted resource.
spring.jta.bitronix.properties.default-transaction-timeout=60 # Default transaction timeout in seconds.
spring.jta.bitronix.properties.disable-jmx=false # Enable JMX support.
spring.jta.bitronix.properties.exception-analyzer= 
# Set the fully qualified name of the exception analyzer implementation to use.
spring.jta.bitronix.properties.filter-log-status=false 
# Enable filtering of logs so that only mandatory logs are written.
spring.jta.bitronix.properties.force-batching-enabled=true #  Set if disk forces are batched.
spring.jta.bitronix.properties.forced-write-enabled=true # Set if logs are forced to disk.
spring.jta.bitronix.properties.graceful-shutdown-interval=60 
# Maximum amount of seconds the TM will wait for transactions 
# to get done before aborting them at shutdown time.
spring.jta.bitronix.properties.jndi-transaction-synchronization-registry-name= 
# JNDI name of the TransactionSynchronizationRegistry.
spring.jta.bitronix.properties.jndi-user-transaction-name= # JNDI name of the UserTransaction.
spring.jta.bitronix.properties.journal=disk # Name of the journal. Can be 'disk', 'null' or a class name.
spring.jta.bitronix.properties.log-part1-filename=btm1.tlog # Name of the first fragment of the journal.
spring.jta.bitronix.properties.log-part2-filename=btm2.tlog # Name of the second fragment of the journal.
spring.jta.bitronix.properties.max-log-size-in-mb=2 # Maximum size in megabytes of the journal fragments.
spring.jta.bitronix.properties.resource-configuration-filename= # ResourceLoader configuration file name.
spring.jta.bitronix.properties.server-id= 
# ASCII ID that must uniquely identify this TM instance. Default to the machine's IP address.
spring.jta.bitronix.properties.skip-corrupted-logs=false # Skip corrupted transactions log entries.
spring.jta.bitronix.properties.warn-about-zero-resource-transaction=true 
# Log a warning for transactions executed without a single enlisted resource.
 
# NARAYANA (NarayanaProperties)
spring.jta.narayana.default-timeout=60 # Transaction timeout in seconds.
spring.jta.narayana.expiry-scanners=
com.arjuna.ats.internal.arjuna.recovery.ExpiredTransactionStatusManagerScanner 
# Comma-separated list of expiry scanners.
spring.jta.narayana.log-dir= # Transaction object store directory.
spring.jta.narayana.one-phase-commit=true # Enable one phase commit optimisation.
spring.jta.narayana.periodic-recovery-period=120 
# Interval in which periodic recovery scans are performed in seconds.
spring.jta.narayana.recovery-backoff-period=10 
# Back off period between first and second phases of the recovery scan in seconds.
spring.jta.narayana.recovery-db-pass= # Database password to be used by recovery manager.
spring.jta.narayana.recovery-db-user= # Database username to be used by recovery manager.
spring.jta.narayana.recovery-jms-pass= # JMS password to be used by recovery manager.
spring.jta.narayana.recovery-jms-user= # JMS username to be used by recovery manager.
spring.jta.narayana.recovery-modules= # Comma-separated list of recovery modules.
spring.jta.narayana.transaction-manager-id=1 # Unique transaction manager id.
spring.jta.narayana.xa-resource-orphan-filters= # Comma-separated list of orphan filters.
```