package net.iceburg.boot.starter.autoconfigure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.iceburg.boot.starter.autoconfigure.LogAutoConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LogAutoConfiguration.class)
public class SayHelloAutoConfigurationTest {

  @Test
  public void whenSpringContextIsBootstrapped_thenNoExceptions() {
  }

  // @TODO implement test that confirms JSON output is used by !dev and !local profiles
  // @TODO implement test that enables tracing on iceburg loggers based on iceburg.log.trace_enabled propery

  // @TODO implement more testing
  // https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-test-autoconfig

}
