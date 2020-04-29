package net.iceburg.boot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AskJeeves {

  @Autowired private Environment env;

  public Boolean isProfileActive(String profile) {
    return Arrays.stream(env.getActiveProfiles()).anyMatch(profile::equals);
  }
}
