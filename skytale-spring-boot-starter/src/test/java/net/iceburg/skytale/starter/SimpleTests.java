package net.iceburg.skytale.starter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class SimpleTests {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

  @Test
  void runApplicationContext() {
    this.contextRunner.run((context) -> assertThat("foo").isEqualTo("foo"));
  }
}
