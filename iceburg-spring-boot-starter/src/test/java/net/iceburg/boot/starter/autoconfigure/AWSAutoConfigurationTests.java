package net.iceburg.boot.starter.autoconfigure;

import net.iceburg.boot.starter.autoconfigure.AWSAutoConfiguration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.assertj.AssertableApplicationContext;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.s3.S3Client;

//@SpringBootTest
public class AWSAutoConfigurationTests {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
    .withConfiguration(AutoConfigurations.of(AWSAutoConfiguration.class));

  @Test
  //@Disabled
	public void testS3Client() {
    this.contextRunner.withUserConfiguration(BaseConfiguration.class)
      .withPropertyValues("test-name=withConfiguredJobAndTrigger")
      //withSystemProperties("spring.profiles.active=UNITTEST")
  		.run((context) -> {
  			assertThat(context).hasSingleBean(S3Client.class);

        //@TODO test endpoint behavior
  		});
	}

  @Configuration(proxyBeanMethods = false)
  static class BaseConfiguration {

  }


}
