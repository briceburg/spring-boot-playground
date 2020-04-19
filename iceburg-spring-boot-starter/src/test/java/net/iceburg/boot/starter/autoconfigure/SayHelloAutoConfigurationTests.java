package net.iceburg.boot.starter.autoconfigure;

import net.iceburg.boot.starter.SayHello;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.assertj.AssertableApplicationContext;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class SayHelloAutoConfigurationTests {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
    .withConfiguration(AutoConfigurations.of(SayHelloAutoConfiguration.class));


  @Test
  public void whenSpringContextIsBootstrapped_thenNoExceptions() {
  }


  @Test
	public void tracesPrintWhenEnabled(CapturedOutput capturedOutput) {
    this.contextRunner.withUserConfiguration(BaseConfiguration.class)
      .withPropertyValues("test-name=withConfiguredJobAndTrigger")
  		.run((context) -> {
  			assertThat(context).hasSingleBean(SayHello.class);
        SayHello sayHello = context.getBean(SayHello.class);
        sayHello.msg();
        assertThat(capturedOutput).contains("foooo").contains("baaaarr");
  		});
	}

  @Configuration(proxyBeanMethods = false)
  static class BaseConfiguration {

  }

  // @TODO implement test that confirms JSON output is used by !dev and !local profiles
  // @TODO implement test that enables tracing on iceburg loggers based on iceburg.log.trace_enabled propery

  // @TODO implement more testing
  // https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-test-autoconfig

}
