package net.iceburg.skytale.encodeworker;

import net.iceburg.skytale.protobuf.SkytaleMessages;
import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.component.aws2.s3.AWS2S3Constants;
import org.apache.camel.dataformat.protobuf.ProtobufDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;

@Component
@EnableConfigurationProperties({ SkytaleProperties.class })
public class CamelRoutes extends EndpointRouteBuilder {
    @Autowired
    private SkytaleProperties skytaleProperties;

    @Autowired
    private EncodeOMatic encodeOMatic;

    @Override
    public void configure() {

        ProtobufDataFormat format = new ProtobufDataFormat(SkytaleMessages.EncodeMessage.getDefaultInstance());
        format.setContentTypeFormat("json");

        from(aws2Sqs(skytaleProperties.queueNames.encode).waitTimeSeconds(20))
                /*
                // leaving inline processor for reference
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setHeader(AWS2S3Constants.KEY, "camelKey");
                    }
                })
                 */
                // we unmarshall the protocol buffer from JSON as SQS is unformatted text only.
                .unmarshal(format)
                // set the S3 filename to the uuid field in the EncodeMessage
                .setHeader(AWS2S3Constants.KEY, simple("${body.uuid}"))
                .bean(encodeOMatic, "encodeMessage")
                .to(aws2S3(skytaleProperties.bucketNames.secrets))
                .to(file("data/output"));
    }
}
