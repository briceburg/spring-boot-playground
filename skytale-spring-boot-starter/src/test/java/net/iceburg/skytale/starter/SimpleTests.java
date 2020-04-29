package net.iceburg.skytale.starter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleTests {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

  @Test
  void runApplicationContext() {
    this.contextRunner.run((context) -> assertThat("foo").isEqualTo("foo"));
  }
}
