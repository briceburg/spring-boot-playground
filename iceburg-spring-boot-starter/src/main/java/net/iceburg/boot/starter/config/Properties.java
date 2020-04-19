package net.iceburg.boot.starter.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("iceburg")
public class Properties {

  private List<String> validProfiles = List.of(
    Constant.PROFILE_CLOUD_AWS,
    Constant.PROFILE_LOCAL
  );
}
