package net.iceburg.boot.starter.autoconfigure;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import net.iceburg.boot.starter.config.IceburgConstant;
import net.iceburg.boot.starter.config.IceburgProperties;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(IceburgProperties.class)
public class LoggerAutoConfiguration {

  public LoggerAutoConfiguration(IceburgProperties iceburgProperties) {
    if (iceburgProperties.traceEnabled) {
      LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
      loggerContext.getLogger(IceburgConstant.LOGGER_NS).setLevel(Level.TRACE);
    }
  }
}
