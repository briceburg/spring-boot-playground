package net.iceburg.boot.starter.autoconfigure;

import net.iceburg.boot.starter.SayHello;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @ConditionalOnProperty(value = "eventstarter.enabled", havingValue = "true")
// @ConditionalOnClass(name = "io.reflectoring.KafkaConnector")
@Configuration
@ConditionalOnClass(SayHello.class)
@AutoConfigureAfter(LoggerAutoConfiguration.class)
public class SayHelloAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public SayHello sayHello() {
    return new SayHello();
  }
}
