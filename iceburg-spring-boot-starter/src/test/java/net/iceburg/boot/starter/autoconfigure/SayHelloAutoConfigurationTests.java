package net.iceburg.boot.starter.autoconfigure;

import net.iceburg.boot.starter.SayHello;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class SayHelloAutoConfigurationTests {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(SayHelloAutoConfiguration.class));

  @Test
  public void contextLoads(CapturedOutput capturedOutput) {
    assertThat("foo").isEqualTo("foo");
  }

  // @TODO implement test that confirms JSON output is used by !dev and !local profiles

  // @TODO implement more testing
  // https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-test-autoconfig

  @Test
  public void tracesPrintWhenEnabled(CapturedOutput capturedOutput) {
    this.contextRunner
        .withUserConfiguration(BaseConfiguration.class)
        .withPropertyValues("test-name=withConfiguredJobAndTrigger")
        // withSystemProperties("spring.profiles.active=UNITTEST")
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(SayHello.class);

              SayHello sayHello = context.getBean(SayHello.class);
              sayHello.msg(context);
              assertThat(capturedOutput).contains("net.iceburg");
            });
  }

  @Configuration(proxyBeanMethods = false)
  static class BaseConfiguration {}
}
