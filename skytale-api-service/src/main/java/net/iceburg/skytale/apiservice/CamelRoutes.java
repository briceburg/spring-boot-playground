package net.iceburg.skytale.apiservice;

import net.iceburg.skytale.protobuf.SkytaleMessages;
import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.dataformat.protobuf.ProtobufDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(SkytaleProperties.class)
public class CamelRoutes extends EndpointRouteBuilder {
    @Autowired
    private SkytaleProperties skytaleProperties;

    @Override
    public void configure() {

        // direct:encodeQueue ships a String body to the SQS encode queue
        from(direct("encodeQueue"))
                .to(aws2Sqs(skytaleProperties.queueNames.encode));


        // direct:decodeQueue marshals protobuf message to JSON before shipping String body to SQS decode queue
        ProtobufDataFormat format = new ProtobufDataFormat(SkytaleMessages.DecodeMessage.getDefaultInstance());
        format.setContentTypeFormat("json");

        from(direct("decodeQueue"))
                .marshal(format)
                .to(aws2Sqs(skytaleProperties.queueNames.decode));
    }

}