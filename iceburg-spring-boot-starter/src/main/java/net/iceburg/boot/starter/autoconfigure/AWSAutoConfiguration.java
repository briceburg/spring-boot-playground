package net.iceburg.boot.starter.autoconfigure;

import java.net.URI;

import net.iceburg.boot.starter.EnvironmentHelper;
import net.iceburg.boot.starter.config.LocalProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3AsyncClientBuilder;

@Configuration
@ConditionalOnClass(S3Client.class)
@EnableConfigurationProperties(LocalProperties.class)
public class AWSAutoConfiguration extends EnvironmentHelper {

	@Value("${AWS_REGION:us-east-1}")
	private String awsRegion;

	@Autowired
	private LocalProperties localProperties;

	@Bean
	@ConditionalOnMissingBean
	public S3Client s3Client(){
	  S3ClientBuilder builder = S3Client.builder();
		this.decorateBuilder(builder, localProperties.awsS3Endpoint);
		return builder.build();
	}


	protected void decorateBuilder(AwsClientBuilder builder, String localEndpoint){

		if(super.isProfileActive("local")){
			builder.endpointOverride(URI.create(localEndpoint));
		}

		builder.region(Region.of(awsRegion));
  }
}
