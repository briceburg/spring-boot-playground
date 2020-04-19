package net.iceburg.boot.starter;

import lombok.extern.slf4j.Slf4j;
import static net.logstash.logback.argument.StructuredArguments.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SayHello {

  @Autowired
  private ApplicationContext applicationContext;

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  BuildProperties buildProperties;

  @EventListener
  public void handleContextStart(final ContextRefreshedEvent event) {
  	this.msg();
  }

  public void msg() {

  	log.info("{} v{}",
  		v("iceburg.starter.name", this.getClass().getPackage().getImplementationTitle()),
  		v("iceburg.starter.version", this.getClass().getPackage().getImplementationVersion())
  	);

  	log.info("{} v{}: {} {}",
  		v("spring.application.name", applicationName),
  		v("build.version", buildProperties.getVersion()),
  		kv("spring.profiles.active", applicationContext.getEnvironment().getActiveProfiles()),
  		kv("build.time", buildProperties.getTime())
  	);

    // TODO -- base on a property.
  	log.trace("This is a TRACE message");
  	log.debug("This is a DEBUG message");
    log.info("This is an INFO message");
    log.warn("This is a WARN message");
    log.error("This is an ERROR message");
  }
}
