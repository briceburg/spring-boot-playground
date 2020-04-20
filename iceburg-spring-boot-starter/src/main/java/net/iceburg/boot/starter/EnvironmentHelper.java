package net.iceburg.boot.starter;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class EnvironmentHelper {

  @Autowired
  private Environment env;


  protected Boolean isProfileActive(String profile){
    return Arrays.stream(env.getActiveProfiles()).anyMatch(profile::equals);
  }
}
