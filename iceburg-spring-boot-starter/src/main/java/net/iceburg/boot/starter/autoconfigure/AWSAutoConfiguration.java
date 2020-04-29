package net.iceburg.boot.starter.autoconfigure;

import net.iceburg.boot.starter.AskJeeves;
import net.iceburg.boot.starter.config.LocalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.KmsClientBuilder;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3AsyncClientBuilder;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.SnsClientBuilder;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;

import java.net.URI;

@Configuration
@ConditionalOnClass(S3Client.class)
@EnableConfigurationProperties(LocalProperties.class)
@AutoConfigureAfter(AskJeevesAutoConfiguration.class)
public class AWSAutoConfiguration {

  @Autowired private AskJeeves askJeeves;

  @Value("${AWS_REGION:us-east-1}")
  private String awsRegion;

  @Autowired private LocalProperties localProperties;

  @Bean
  @ConditionalOnMissingBean
  public KmsClient kmsClient() {
    KmsClientBuilder builder = KmsClient.builder();
    this.decorateBuilder(builder, localProperties.awsKMSEndpoint);
    return builder.build();
  }

  @Bean
  @ConditionalOnMissingBean
  public S3AsyncClient s3AsyncClient() {
    S3AsyncClientBuilder builder = S3AsyncClient.builder();
    this.decorateBuilder(builder, localProperties.awsS3Endpoint);
    return builder.build();
  }

  @Bean
  @ConditionalOnMissingBean
  public S3Client s3Client() {
    S3ClientBuilder builder = S3Client.builder();
    this.decorateBuilder(builder, localProperties.awsS3Endpoint);
    return builder.build();
  }

  @Bean
  @ConditionalOnMissingBean
  public SnsClient snsClient() {
    SnsClientBuilder builder = SnsClient.builder();
    this.decorateBuilder(builder, localProperties.awsSNSEndpoint);
    return builder.build();
  }

  @Bean
  @ConditionalOnMissingBean
  public SqsClient sqsClient() {
    SqsClientBuilder builder = SqsClient.builder();
    this.decorateBuilder(builder, localProperties.awsSQSEndpoint);
    return builder.build();
  }

  protected void decorateBuilder(AwsClientBuilder builder, String localEndpoint) {

    if (askJeeves.isProfileActive("local")) {
      builder.endpointOverride(URI.create(localEndpoint));
    }

    builder.region(Region.of(awsRegion));
  }
}
