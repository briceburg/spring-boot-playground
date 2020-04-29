package net.iceburg.boot.starter;

import lombok.extern.slf4j.Slf4j;
import net.iceburg.boot.starter.config.IceburgConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.argument.StructuredArguments.v;

@Slf4j
@Component
public class SayHello {

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired(required = false)
  BuildProperties buildProperties;

  @EventListener
  public void handleContextStart(final ContextRefreshedEvent event) {
    this.msg(event.getApplicationContext());
  }

  public void msg(ApplicationContext context) {

    String buildVersion = buildProperties == null ? "unknown" : buildProperties.getVersion();
    Instant buildTime = buildProperties == null ? Instant.now() : buildProperties.getTime();

    log.info(
        "{} v{}",
        v("iceburg.starter.name", this.getClass().getPackage().getImplementationTitle()),
        v("iceburg.starter.version", this.getClass().getPackage().getImplementationVersion()));

    log.info(
        "{} v{}: {} {}",
        v("spring.application.name", applicationName),
        v("build.version", buildVersion),
        kv("spring.profiles.active", context.getEnvironment().getActiveProfiles()),
        kv("build.time", buildTime));

    log.trace("trace enabled in the {} namespace", IceburgConstant.LOGGER_NS);
  }
}
