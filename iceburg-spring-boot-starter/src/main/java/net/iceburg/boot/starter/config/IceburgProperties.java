package net.iceburg.boot.starter.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("iceburg")
public class IceburgProperties {

  public List<String> validProfiles =
      List.of(IceburgConstant.PROFILE_CLOUD_AWS, IceburgConstant.PROFILE_LOCAL);

  public Boolean traceEnabled = true;
}
