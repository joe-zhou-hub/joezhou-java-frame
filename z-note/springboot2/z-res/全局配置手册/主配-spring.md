**配置：**
```properties

 
# GROOVY TEMPLATES (GroovyTemplateAutoConfiguration)
spring.groovy.template.allow-request-override=false 
# Set whether HttpServletRequest attributes are allowed to override (hide) controller 
# generated model attributes of the same name.
spring.groovy.template.allow-session-override=false 
# Set whether HttpSession attributes are allowed to override (hide) controller 
# generated model attributes of the same name.
spring.groovy.template.cache= # Enable template caching.
spring.groovy.template.charset=UTF-8 # Template encoding.
spring.groovy.template.check-template-location=true # Check that the templates location exists.
spring.groovy.template.configuration.*= # See GroovyMarkupConfigurer
spring.groovy.template.content-type=test/html # Content-Type value.
spring.groovy.template.enabled=true # Enable MVC view resolution for this technology.
spring.groovy.template.expose-request-attributes=false 
# Set whether all request attributes should be added to the model prior to merging with the template.
spring.groovy.template.expose-session-attributes=false 
# Set whether all HttpSession attributes should be added to the model prior to merging with the template.
spring.groovy.template.expose-spring-macro-helpers=true 
# Set whether to expose a RequestContext for use by Spring's macro library, 
# under the name "springMacroRequestContext".
spring.groovy.template.prefix= # Prefix that gets prepended to view names when building a URL.
spring.groovy.template.request-context-attribute= # Name of the RequestContext attribute for all views.
spring.groovy.template.resource-loader-path=classpath:/templates/ # Template path.
spring.groovy.template.suffix=.tpl # Suffix that gets appended to view names when building a URL.
spring.groovy.template.view-names= # White list of view names that can be resolved.
 
# SPRING HATEOAS (HateoasProperties)
spring.hateoas.use-hal-as-default-json-media-type=true 
# Specify if application/hal+json responses should be sent to requests that accept application/json.
 
# HTTP message conversion
spring.http.converters.preferred-json-mapper=jackson 
# Preferred JSON mapper to use for HTTP message conversion. 
# Set to "gson" to force the use of Gson when both it and Jackson are on the classpath.
 
# HTTP encoding (HttpEncodingProperties)
spring.http.encoding.charset=UTF-8 
# Charset of HTTP requests and responses. Added to the "Content-Type" header if not set explicitly.
spring.http.encoding.enabled=true # Enable http encoding support.
spring.http.encoding.force= # Force the encoding to the configured charset on HTTP requests and responses.
spring.http.encoding.force-request= 
# Force the encoding to the configured charset on HTTP requests. 
# Defaults to true when "force" has not been specified.
spring.http.encoding.force-response= # Force the encoding to the configured charset on HTTP responses.
spring.http.encoding.mapping= # Locale to Encoding mapping.
 
# MULTIPART (MultipartProperties)
spring.http.multipart.enabled=true # Enable support of multi-part uploads.
spring.http.multipart.file-size-threshold=0 
# Threshold after which files will be written to disk. 
# Values can use the suffixed "MB" or "KB" to indicate a Megabyte or Kilobyte size.
spring.http.multipart.location= # Intermediate location of uploaded files.
spring.http.multipart.max-file-size=1MB 
# Max file size. Values can use the suffixed "MB" or "KB" to indicate a Megabyte or Kilobyte size.
spring.http.multipart.max-request-size=10MB 
# Max request size. Values can use the suffixed "MB" or "KB" to indicate a Megabyte or Kilobyte size.
spring.http.multipart.resolve-lazily=false 
# Whether to resolve the multipart request lazily at the time of file or parameter access.
 
# JACKSON (JacksonProperties)
spring.jackson.date-format= 
# Date format string or a fully-qualified date format class name. For instance `yyyy-MM-dd HH:mm:ss`.
spring.jackson.default-property-inclusion= # Controls the inclusion of properties during serialization.
spring.jackson.deserialization.*= 
# Jackson on/off features that affect the way Java objects are deserialized.
spring.jackson.generator.*= # Jackson on/off features for generators.
spring.jackson.joda-date-time-format= 
# Joda date time format string. If not configured, 
# "date-format" will be used as a fallback if it is configured with a format string.
spring.jackson.locale= # Locale used for formatting.
spring.jackson.mapper.*= # Jackson general purpose on/off features.
spring.jackson.parser.*= # Jackson on/off features for parsers.
spring.jackson.property-naming-strategy= 
# One of the constants on Jackson's PropertyNamingStrategy. 
# Can also be a fully-qualified class name of a PropertyNamingStrategy subclass.
spring.jackson.serialization.*= # Jackson on/off features that affect the way Java objects are serialized.
spring.jackson.time-zone= # Time zone used when formatting dates. For instance `America/Los_Angeles`
 
# JERSEY (JerseyProperties)
spring.jersey.application-path= 
# Path that serves as the base URI for the application. 
# Overrides the value of "@ApplicationPath" if specified.
spring.jersey.filter.order=0 # Jersey filter chain order.
spring.jersey.init.*= # Init parameters to pass to Jersey via the servlet or filter.
spring.jersey.servlet.load-on-startup=-1 # Load on startup priority of the Jersey servlet.
spring.jersey.type=servlet # Jersey integration type.
 
# SPRING LDAP (LdapProperties)
spring.ldap.urls= # LDAP URLs of the server.
spring.ldap.base= # Base suffix from which all operations should originate.
spring.ldap.username= # Login user of the server.
spring.ldap.password= # Login password of the server.
spring.ldap.base-environment.*= # LDAP specification settings.
 
# EMBEDDED LDAP (EmbeddedLdapProperties)
spring.ldap.embedded.base-dn= # The base DN
spring.ldap.embedded.credential.username= # Embedded LDAP username.
spring.ldap.embedded.credential.password= # Embedded LDAP password.
spring.ldap.embedded.ldif=classpath:schema.ldif # Schema (LDIF) script resource reference.
spring.ldap.embedded.port= # Embedded LDAP port.
spring.ldap.embedded.validation.enabled=true # Enable LDAP schema validation.
spring.ldap.embedded.validation.schema= # Path to the custom schema.
 
# SPRING MOBILE DEVICE VIEWS (DeviceDelegatingViewResolverAutoConfiguration)
spring.mobile.devicedelegatingviewresolver.enable-fallback=false # Enable support for fallback resolution.
spring.mobile.devicedelegatingviewresolver.enabled=false # Enable device view resolver.
spring.mobile.devicedelegatingviewresolver.mobile-prefix=mobile/ 
# Prefix that gets prepended to view names for mobile devices.
spring.mobile.devicedelegatingviewresolver.mobile-suffix= 
# Suffix that gets appended to view names for mobile devices.
spring.mobile.devicedelegatingviewresolver.normal-prefix= 
# Prefix that gets prepended to view names for normal devices.
spring.mobile.devicedelegatingviewresolver.normal-suffix= 
# Suffix that gets appended to view names for normal devices.
spring.mobile.devicedelegatingviewresolver.tablet-prefix=tablet/ 
# Prefix that gets prepended to view names for tablet devices.
spring.mobile.devicedelegatingviewresolver.tablet-suffix= 
# Suffix that gets appended to view names for tablet devices.
 
# SPRING MOBILE SITE PREFERENCE (SitePreferenceAutoConfiguration)
spring.mobile.sitepreference.enabled=true # Enable SitePreferenceHandler.
 
# MUSTACHE TEMPLATES (MustacheAutoConfiguration)
spring.mustache.allow-request-override= 
# Set whether HttpServletRequest attributes are allowed to override (hide) controller 
# generated model attributes of the same name.
spring.mustache.allow-session-override= 
# Set whether HttpSession attributes are allowed to override (hide) controller 
# generated model attributes of the same name.
spring.mustache.cache= # Enable template caching.
spring.mustache.charset= # Template encoding.
spring.mustache.check-template-location= # Check that the templates location exists.
spring.mustache.content-type= # Content-Type value.
spring.mustache.enabled= # Enable MVC view resolution for this technology.
spring.mustache.expose-request-attributes= 
# Set whether all request attributes should be added to the model prior to merging with the template.
spring.mustache.expose-session-attributes= 
# Set whether all HttpSession attributes should be added to the model prior to merging with the template.
spring.mustache.expose-spring-macro-helpers= 
# Set whether to expose a RequestContext for use by Spring's macro library, 
# under the name "springMacroRequestContext".
spring.mustache.prefix=classpath:/templates/ # Prefix to apply to template names.
spring.mustache.request-context-attribute= # Name of the RequestContext attribute for all views.
spring.mustache.suffix=.html # Suffix to apply to template names.
spring.mustache.view-names= # White list of view names that can be resolved.
 
# SPRING MVC (WebMvcProperties)
spring.mvc.async.request-timeout= 
# Amount of time (in milliseconds) before asynchronous request handling times out.
spring.mvc.date-format= # Date format to use. For instance `dd/MM/yyyy`.
spring.mvc.dispatch-trace-request=false # Dispatch TRACE requests to the FrameworkServlet doService method.
spring.mvc.dispatch-options-request=true 
# Dispatch OPTIONS requests to the FrameworkServlet doService method.
spring.mvc.favicon.enabled=true # Enable resolution of favicon.ico.
spring.mvc.formcontent.putfilter.enabled=true # Enable Spring's HttpPutFormContentFilter.
spring.mvc.ignore-default-model-on-redirect=true 
# If the content of the "default" model should be ignored during redirect scenarios.
spring.mvc.locale= # Locale to use. By default, this locale is overridden by the "Accept-Language" header.
spring.mvc.locale-resolver=accept-header # Define how the locale should be resolved.
spring.mvc.log-resolved-exception=false 
# Enable warn logging of exceptions resolved by a "HandlerExceptionResolver".
spring.mvc.media-types.*= # Maps file extensions to media types for content negotiation.
spring.mvc.message-codes-resolver-format= 
# Formatting strategy for message codes. For instance `PREFIX_ERROR_CODE`.
spring.mvc.servlet.load-on-startup=-1 # Load on startup priority of the Spring Web Services servlet.
spring.mvc.static-path-pattern=/** # Path pattern used for static resources.
spring.mvc.throw-exception-if-no-handler-found=false 
# If a "NoHandlerFoundException" should be thrown if no Handler was found to process a request.
spring.mvc.view.prefix= # Spring MVC view prefix.
spring.mvc.view.suffix= # Spring MVC view suffix.
 
# SPRING RESOURCES HANDLING (ResourceProperties)
spring.resources.add-mappings=true # Enable default resource handling.
spring.resources.cache-period= # Cache period for the resources served by the resource handler, in seconds.
spring.resources.chain.cache=true # Enable caching in the Resource chain.
spring.resources.chain.enabled= 
# Enable the Spring Resource Handling chain. 
# Disabled by default unless at least one strategy has been enabled.
spring.resources.chain.gzipped=false # Enable resolution of already gzipped resources.
spring.resources.chain.html-application-cache=false # Enable HTML5 application cache manifest rewriting.
spring.resources.chain.strategy.content.enabled=false # Enable the content Version Strategy.
spring.resources.chain.strategy.content.paths=/** 
# Comma-separated list of patterns to apply to the Version Strategy.
spring.resources.chain.strategy.fixed.enabled=false # Enable the fixed Version Strategy.
spring.resources.chain.strategy.fixed.paths=/** 
# Comma-separated list of patterns to apply to the Version Strategy.
spring.resources.chain.strategy.fixed.version= # Version string to use for the Version Strategy.
spring.resources.static-locations=
classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/ 
# Locations of static resources.
 
# SPRING SESSION (SessionProperties)
spring.session.hazelcast.flush-mode=on-save # Sessions flush mode.
spring.session.hazelcast.map-name=spring:session:sessions # Name of the map used to store sessions.
spring.session.jdbc.initializer.enabled= 
# Create the required session tables on startup if necessary. 
# Enabled automatically if the default table name is set or a custom schema is configured.
spring.session.jdbc.schema=classpath:org/springframework/session/jdbc/schema-@@platform@@.sql 
# Path to the SQL file to use to initialize the database schema.
spring.session.jdbc.table-name=SPRING_SESSION # Name of database table used to store sessions.
spring.session.mongo.collection-name=sessions # Collection name used to store sessions.
spring.session.redis.flush-mode=on-save # Sessions flush mode.
spring.session.redis.namespace= # Namespace for keys used to store sessions.
spring.session.store-type= # Session store type.
 
# SPRING SOCIAL (SocialWebAutoConfiguration)
spring.social.auto-connection-views=false # Enable the connection status view for supported providers.
 
# SPRING SOCIAL FACEBOOK (FacebookAutoConfiguration)
spring.social.facebook.app-id= # your application's Facebook App ID
spring.social.facebook.app-secret= # your application's Facebook App Secret
 
# SPRING SOCIAL LINKEDIN (LinkedInAutoConfiguration)
spring.social.linkedin.app-id= # your application's LinkedIn App ID
spring.social.linkedin.app-secret= # your application's LinkedIn App Secret
 
# SPRING SOCIAL TWITTER (TwitterAutoConfiguration)
spring.social.twitter.app-id= # your application's Twitter App ID
spring.social.twitter.app-secret= # your application's Twitter App Secret
 
# THYMELEAF (ThymeleafAutoConfiguration)
spring.thymeleaf.cache=true # Enable template caching.
spring.thymeleaf.check-template=true # Check that the template exists before rendering it.
spring.thymeleaf.check-template-location=true # Check that the templates location exists.
spring.thymeleaf.content-type=text/html # Content-Type value.
spring.thymeleaf.enabled=true # Enable MVC Thymeleaf view resolution.
spring.thymeleaf.encoding=UTF-8 # Template encoding.
spring.thymeleaf.excluded-view-names= 
# Comma-separated list of view names that should be excluded from resolution.
spring.thymeleaf.mode=HTML5 
# Template mode to be applied to templates. See also StandardTemplateModeHandlers.
spring.thymeleaf.prefix=classpath:/templates/ 
# Prefix that gets prepended to view names when building a URL.
spring.thymeleaf.suffix=.html # Suffix that gets appended to view names when building a URL.
spring.thymeleaf.template-resolver-order= # Order of the template resolver in the chain.
spring.thymeleaf.view-names= # Comma-separated list of view names that can be resolved.
 
# SPRING WEB SERVICES (WebServicesProperties)
spring.webservices.path=/services # Path that serves as the base URI for the services.
spring.webservices.servlet.init= # Servlet init parameters to pass to Spring Web Services.
spring.webservices.servlet.load-on-startup=-1 
# Load on startup priority of the Spring Web Services servlet.
```