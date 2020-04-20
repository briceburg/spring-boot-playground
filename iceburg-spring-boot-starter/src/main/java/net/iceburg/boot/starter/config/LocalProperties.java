package net.iceburg.boot.starter.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("local")
public class LocalProperties {

  // ports are based on localstack defaults (https://github.com/localstack/localstack)
  public String awsKMSEndpoint = "http://localhost:4599";
  public String awsS3Endpoint = "http://localhost:4572";
  public String awsSNSEndpoint = "http://localhost:4575";
  public String awsSQSEndpoint = "http://localhost:4576";
}
