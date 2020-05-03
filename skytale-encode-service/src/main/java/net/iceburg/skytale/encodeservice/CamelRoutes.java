package net.iceburg.skytale.encodeservice;

import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.apache.camel.support.DefaultHeaderFilterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(SkytaleProperties.class)
public class CamelRoutes extends EndpointRouteBuilder {
    @Autowired
    private SkytaleProperties skytaleProperties;

    @Override
    public void configure() {
        from(direct("encodeQueue"))
                .to(aws2Sqs(skytaleProperties.queueNames.encode));
    }

}