package net.iceburg.skytale.decodeworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DecodeWorkerApplication {

  public static void main(String[] args) throws Exception {
    new SpringApplication(DecodeWorkerApplication.class).run(args);
  }
}
