package net.iceburg.skytale.encodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class EncodeServiceApplication {

  public static void main(String[] args) throws Exception {
    new SpringApplication(EncodeServiceApplication.class).run(args);
  }
}

