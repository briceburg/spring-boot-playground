# iceburg-spring-boot-starter

* structured log configuration via logback and logstash encoder
  * no need to provide logback.xml in your application config
  * example/see SayHello for example of using structured logging
* by default, TRACE is enabled for all net.iceburg loggers. to disable set `iceburg.traceEnabled=false` in application properties
