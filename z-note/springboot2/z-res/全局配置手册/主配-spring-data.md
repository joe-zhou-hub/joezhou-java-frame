# spring.data.neo4j

```properties
# Compiler to use.
spring.data.neo4j.compiler= 

# Enable embedded mode if the embedded driver is available.
spring.data.neo4j.embedded.enabled=true

# Register OpenSessionInViewInterceptor. 
# Binds a Neo4j Session to the thread for the entire processing of the request.
spring.data.neo4j.open-in-view=false 

# Enable Neo4j repositories.
spring.data.neo4j.repositories.enabled=true 

# Login password of the server.
spring.data.neo4j.password= 

# URI used by the driver. Auto-detected by default.
spring.data.neo4j.uri= 

# Login user of the server.
spring.data.neo4j.username= 
```

# spring.data.rest

```properties
# Base path to be used by Spring Data REST to expose repository resources.
spring.data.rest.base-path= 

# Default size of pages.
spring.data.rest.default-page-size= 

# Strategy to use to determine which repositories get exposed.
spring.data.rest.detection-strategy=default 

# Enable enum value translation via the Spring Data REST default resource bundle.
spring.data.rest.enable-enum-translation= 

# Name of the URL query string parameter that indicates how many results to return at once.
spring.data.rest.limit-param-name= 

# Maximum size of pages.
spring.data.rest.max-page-size= 

# Name of the URL query string parameter that indicates what page to return.
spring.data.rest.page-param-name= 

# Return a response body after creating an entity.
spring.data.rest.return-body-on-create= 

# Return a response body after updating an entity.
spring.data.rest.return-body-on-update= 

# Name of the URL query string parameter that indicates what direction to sort results.
spring.data.rest.sort-param-name= 
```

# spring.data.couchbase

```properties
# Automatically create views and indexes.
spring.data.couchbase.auto-index=false

# Enable Couchbase repositories.
spring.data.couchbase.repositories.enabled=true 

# Consistency to apply by default on generated queries.
spring.data.couchbase.consistency=read-your-own-writes 
```

# spring.data.solr

```properties
# SOLR (SolrProperties)

# Solr host. Ignored if "zk-host" is set.
spring.data.solr.host=http://127.0.0.1:8983/solr 

# Enable Solr repositories.
spring.data.solr.repositories.enabled=true 

# ZooKeeper host address in the form HOST:PORT.
spring.data.solr.zk-host= 
```

# spring.data.cassandra

```properties
# Name of the Cassandra cluster.
spring.data.cassandra.cluster-name= 

# Compression supported by the Cassandra binary protocol.
spring.data.cassandra.compression=none 

# Socket option: connection time out.
spring.data.cassandra.connect-timeout-millis= 

# Queries consistency level.
spring.data.cassandra.consistency-level= 

# Comma-separated list of cluster node addresses.
spring.data.cassandra.contact-points=localhost 

# Queries default fetch size.
spring.data.cassandra.fetch-size= 

# Keyspace name to use.
spring.data.cassandra.keyspace-name= 

# Class name of the load balancing policy.
spring.data.cassandra.load-balancing-policy= 

# Port of the Cassandra server.
spring.data.cassandra.port= 

# Login password of the server.
spring.data.cassandra.password= 

# Socket option: read time out.
spring.data.cassandra.read-timeout-millis= 

# Reconnection policy class.
spring.data.cassandra.reconnection-policy= 

# Class name of the retry policy.
spring.data.cassandra.retry-policy= 

# Queries serial consistency level.
spring.data.cassandra.serial-consistency-level= 

# Schema action to take at startup.
spring.data.cassandra.schema-action=none 

# Enable SSL support.
spring.data.cassandra.ssl=false 

# Login user of the server.
spring.data.cassandra.username= 
```