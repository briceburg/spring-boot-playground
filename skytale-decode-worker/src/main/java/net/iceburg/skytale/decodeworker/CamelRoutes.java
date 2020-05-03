package net.iceburg.skytale.decodeworker;

import net.iceburg.skytale.protobuf.SkytaleMessages;
import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.component.aws2.s3.AWS2S3Constants;
import org.apache.camel.dataformat.protobuf.ProtobufDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties({ SkytaleProperties.class })
public class CamelRoutes extends EndpointRouteBuilder {
    @Autowired
    private SkytaleProperties skytaleProperties;

    @Autowired
    private DecodeOMatic decodeOMatic;

    @Override
    public void configure() {

        ProtobufDataFormat format = new ProtobufDataFormat(SkytaleMessages.DecodeMessage.getDefaultInstance());
        format.setContentTypeFormat("json");

        from(aws2Sqs(skytaleProperties.queueNames.decode).waitTimeSeconds(20))
                // we unmarshall the protocol buffer from JSON as SQS is unformatted text only.
                .unmarshal(format)
                .setHeader("outFile", simple("${body.uuid}"))

                // fetch and decode message from secrets bucket into plaintext
                .bean(decodeOMatic, "decodeMessage")

                // write decoded message to plaintext bucket
                .setHeader(AWS2S3Constants.KEY, simple("${header.outFile}"))
                .to(aws2S3(skytaleProperties.bucketNames.plaintext))

                // also write to data/output for your eyes only
                .to(file("data/output"));
    }
}