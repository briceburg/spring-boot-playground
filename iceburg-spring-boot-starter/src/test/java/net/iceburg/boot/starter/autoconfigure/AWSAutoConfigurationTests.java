package net.iceburg.boot.starter.autoconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest
public class AWSAutoConfigurationTests {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(
              AutoConfigurations.of(AskJeevesAutoConfiguration.class, AWSAutoConfiguration.class));

  @Test
  public void testKmsClient() {
    this.contextRunner
        .withUserConfiguration(BaseConfiguration.class)
        .withPropertyValues("test-name=withConfiguredJobAnd Trigger")
        // withSystemProperties("spring.profiles.active=UNITTEST")
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(KmsClient.class);
              // @TODO test endpoint behavior
            });
  }

  @Test
  public void testS3Client() {
    this.contextRunner
        .withUserConfiguration(BaseConfiguration.class)
        .withPropertyValues("test-name=withConfiguredJobAndTrigger")
        // withSystemProperties("spring.profiles.active=UNITTEST")
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(S3Client.class);
              assertThat(context).hasSingleBean(S3AsyncClient.class);

              // @TODO test endpoint behavior
            });
  }

  @Test
  public void testSnsClient() {
    this.contextRunner
        .withUserConfiguration(BaseConfiguration.class)
        .withPropertyValues("test-name=withConfiguredJobAndTrigger")
        // withSystemProperties("spring.profiles.active=UNITTEST")
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(SnsClient.class);
              // @TODO test endpoint behavior
            });
  }

  @Test
  public void testSqsClient() {
    this.contextRunner
        .withUserConfiguration(BaseConfiguration.class)
        .withPropertyValues("test-name=withConfiguredJobAndTrigger")
        // withSystemProperties("spring.profiles.active=UNITTEST")
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(SqsClient.class);
              // @TODO test endpoint behavior
            });
  }

  @Configuration(proxyBeanMethods = false)
  static class BaseConfiguration {}
}
