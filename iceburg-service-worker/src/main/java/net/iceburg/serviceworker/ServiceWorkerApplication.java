package net.iceburg.serviceworker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceWorkerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServiceWorkerApplication.class, args);
	}

	@Override
  public void run(String... args) throws Exception {
  	System.out.println("HELLO.");
  }
}
