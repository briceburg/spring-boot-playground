package net.iceburg.boot.starter.autoconfigure;

import java.util.List;

import net.iceburg.boot.starter.SayHello;
import net.iceburg.boot.starter.config.IceburgProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;


//@ConditionalOnProperty(value = "eventstarter.enabled", havingValue = "true")
//@ConditionalOnClass(name = "io.reflectoring.KafkaConnector")
@Configuration
@EnableConfigurationProperties(IceburgProperties.class)
@ConditionalOnClass(SayHello.class)
public class SayHelloAutoConfiguration {

	@Autowired
  private IceburgProperties iceburgProperties;

	@Bean
	@ConditionalOnMissingBean
	public SayHello sayHello(){
		return new SayHello();
	}
}
