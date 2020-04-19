package net.iceburg.boot.starter.autoconfigure;

import java.util.List;

import net.iceburg.boot.starter.LogDump;
import net.iceburg.boot.starter.config.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@ConditionalOnProperty(value = "eventstarter.enabled", havingValue = "true")
//@ConditionalOnClass(name = "io.reflectoring.KafkaConnector")
@Configuration
@EnableConfigurationProperties(Properties.class)
@ConditionalOnClass(LogDump.class)
public class LogAutoConfiguration {

	@Autowired
  private Properties properties;

	@Bean
	@ConditionalOnMissingBean
	public LogDump logDump(){
		return new LogDump();
	}

}
