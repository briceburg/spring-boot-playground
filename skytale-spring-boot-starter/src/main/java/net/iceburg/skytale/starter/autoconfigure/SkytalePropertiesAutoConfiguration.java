package net.iceburg.skytale.starter.autoconfigure;

import net.iceburg.skytale.starter.SkytaleUUIDWriter;
import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkytalePropertiesAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SkytaleProperties.class)
    SkytaleProperties skytaleProperties(){
        return new SkytaleProperties();
    }
}

