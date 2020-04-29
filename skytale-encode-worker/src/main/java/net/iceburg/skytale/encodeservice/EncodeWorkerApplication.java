package net.iceburg.skytale.encodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EncodeWorkerApplication {

  public static void main(String[] args) throws Exception {
    new SpringApplication(EncodeWorkerApplication.class).run(args);
  }
}
