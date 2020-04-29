package net.iceburg.skytale.encodeservice;

import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties({ SkytaleProperties.class })
public class EncodeQueueRoute extends EndpointRouteBuilder {
    @Autowired
    private SkytaleProperties properties;

    @Override
    public void configure() {
        from(aws2Sqs(properties.encodeQueue)
                .waitTimeSeconds(20)
        ).to(file("/tmp/zzz"));
    }
}
