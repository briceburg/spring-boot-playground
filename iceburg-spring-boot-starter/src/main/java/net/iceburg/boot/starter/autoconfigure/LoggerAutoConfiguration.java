package net.iceburg.boot.starter.autoconfigure;

import net.iceburg.boot.starter.config.IceburgProperties;
import net.iceburg.boot.starter.config.IceburgConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableConfigurationProperties(IceburgProperties.class)
public class LoggerAutoConfiguration {

	public LoggerAutoConfiguration(IceburgProperties iceburgProperties){
		if(iceburgProperties.traceEnabled){
			LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
			loggerContext.getLogger(IceburgConstant.LOGGER_NS).setLevel(Level.TRACE);
		}
	}
}
