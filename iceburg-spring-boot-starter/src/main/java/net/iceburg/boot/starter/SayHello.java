package net.iceburg.boot.starter;

import net.iceburg.boot.starter.config.IceburgConstant;

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

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  BuildProperties buildProperties;

  @EventListener
  public void handleContextStart(final ContextRefreshedEvent event) {
  	this.msg(event.getApplicationContext());
  }

  public void msg(ApplicationContext context) {

  	log.info("{} v{}",
  		v("iceburg.starter.name", this.getClass().getPackage().getImplementationTitle()),
  		v("iceburg.starter.version", this.getClass().getPackage().getImplementationVersion())
  	);

  	log.info("{} v{}: {} {}",
  		v("spring.application.name", applicationName),
  		v("build.version", buildProperties.getVersion()),
  		kv("spring.profiles.active", context.getEnvironment().getActiveProfiles()),
  		kv("build.time", buildProperties.getTime())
  	);

    log.trace("trace enabled in the {} namespace", IceburgConstant.LOGGER_NS);
  }
}
