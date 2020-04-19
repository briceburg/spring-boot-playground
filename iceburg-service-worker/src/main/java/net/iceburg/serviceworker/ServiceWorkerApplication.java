package net.iceburg.serviceworker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.iceburg.boot.starter.LogDump;


@SpringBootApplication
public class ServiceWorkerApplication implements CommandLineRunner {

	@Autowired
  private LogDump logDump;

	public static void main(String[] args) {
		SpringApplication.run(ServiceWorkerApplication.class, args);
	}

	@Override
  public void run(String... args) throws Exception {
  	logDump.dump();
  }

}
