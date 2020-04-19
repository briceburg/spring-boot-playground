package net.iceburg.boot.starter;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.argument.StructuredArguments.v;

import org.springframework.boot.info.BuildProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.context.ApplicationContext;

@NoArgsConstructor
@Slf4j
public class LogDump {

	@Autowired
  private ApplicationContext applicationContext;

	@Value("${spring.application.name}")
	private String applicationName;

	@Autowired
	BuildProperties buildProperties;

	public void dump() {

		log.info("{} v{}",
			v("iceburg.starter.name", this.getClass().getPackage().getImplementationTitle()),
			v("iceburg.starter.version", this.getClass().getPackage().getImplementationVersion())
		);

		log.info("{} v{}: {} {}",
			v("spring.application.name", applicationName),
			v("build.version", buildProperties.getVersion()),
			kv("spring.profiles.active", applicationContext.getEnvironment().getActiveProfiles()),
			kv("build.time", buildProperties.getTime())
		);

		log.trace("This is a TRACE message");
		log.debug("This is a DEBUG message");
    log.info("This is an INFO message");
    log.warn("This is a WARN message");
    log.error("This is an ERROR message");
	}

}
