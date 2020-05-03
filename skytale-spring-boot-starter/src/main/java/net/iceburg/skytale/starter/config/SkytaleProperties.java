package net.iceburg.skytale.starter.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;



@Component
@ConfigurationProperties("skytale")
@PropertySource("classpath:skytale-spring-boot-starter.properties") // in src/main/resources
@Getter
@Setter
public class SkytaleProperties {
    public String kmsKeyId;
    public BucketNames bucketNames;
    public QueueNames queueNames;

    @Getter
    @Setter
    public static class BucketNames {
        public String plaintext, secrets;
    }

    @Getter
    @Setter
    public static class QueueNames {
        public String decode, encode;
    }

}
