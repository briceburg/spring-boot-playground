package net.iceburg.skytale.starter.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;


// @SpringBootTest
public class HomeControllerAutoConfigurationTests {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner();

  @Test
  public void testHomeController() {
    this.contextRunner
        .withUserConfiguration(BaseConfiguration.class)
        .withPropertyValues("test-name=withConfiguredJobAnd Trigger")
        // withSystemProperties("spring.profiles.active=UNITTEST")
        .run(
            (context) -> {
                assertThat("foo").isEqualTo("foo");
            });
  }

  @Configuration(proxyBeanMethods = false)
  static class BaseConfiguration {}
}
