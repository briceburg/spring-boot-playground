package net.iceburg.skytale.starter.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("skytale")
public class SkytaleProperties {
    public String encodeQueue, decodeQueue;
}
