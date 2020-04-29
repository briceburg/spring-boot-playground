package net.iceburg.skytale.starter.autoconfigure;

import net.iceburg.skytale.starter.SkytaleUUIDWriter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkytaleUUIDWriterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SkytaleUUIDWriter.class)
    SkytaleUUIDWriter skytaleUUIDWriter(){
        return new SkytaleUUIDWriter();
    }
}

