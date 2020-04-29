package net.iceburg.skytale.starter;

import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties({ SkytaleProperties.class })
public class SkytaleDefaultCamelRoutes extends EndpointRouteBuilder {

    @Autowired
    private SkytaleProperties properties;


    @Override
    public void configure() {

        from(direct("encodeQueue")).to(aws2Sqs(properties.encodeQueue));
        from(direct("decodeQueue")).to(aws2Sqs(properties.decodeQueue));

        // placeholder examples
        // from(direct("file")).transform(body().append(" foo")).to(file("data/output"));
    }
}
