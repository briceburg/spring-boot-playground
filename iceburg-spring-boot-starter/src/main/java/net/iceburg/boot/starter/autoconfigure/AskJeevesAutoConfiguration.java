package net.iceburg.boot.starter.autoconfigure;

import net.iceburg.boot.starter.AskJeeves;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @ConditionalOnProperty(value = "eventstarter.enabled", havingValue = "true")
// @ConditionalOnClass(name = "io.reflectoring.KafkaConnector")
@Configuration
public class AskJeevesAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AskJeeves askJeeves() {
    return new AskJeeves();
  }
}
