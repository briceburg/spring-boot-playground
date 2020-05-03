package net.iceburg.skytale.apiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ApiServiceApplication {

  public static void main(String[] args) throws Exception {
    new SpringApplication(ApiServiceApplication.class).run(args);
  }
}

