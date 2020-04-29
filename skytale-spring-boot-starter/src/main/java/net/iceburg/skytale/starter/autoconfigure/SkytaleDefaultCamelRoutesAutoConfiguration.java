package net.iceburg.skytale.starter.autoconfigure;

import net.iceburg.skytale.starter.SkytaleDefaultCamelRoutes;
import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ SkytaleProperties.class })
public class SkytaleDefaultCamelRoutesAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SkytaleDefaultCamelRoutes.class)
    //@ConditionalOnProperty(name = "skytale.url")
    SkytaleDefaultCamelRoutes defaultCamelRoutes(){
        return new SkytaleDefaultCamelRoutes();
    }
}