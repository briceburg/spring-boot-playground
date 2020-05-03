package net.iceburg.skytale.encodeservice.impl;

import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import net.iceburg.skytale.openapi.server.api.V1Api;
import net.iceburg.skytale.openapi.server.model.EncodeMessageRequest;
import net.iceburg.skytale.openapi.server.model.EncodeMessageResponse;
import net.iceburg.skytale.protobuf.SkytaleMessages;
import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class SkytaleApiImpl implements V1Api {

    @Autowired
    private FluentProducerTemplate producer;

    @Override
    public ResponseEntity<EncodeMessageResponse> encodeMessage(EncodeMessageRequest encodeMessageRequest) throws Exception {
        UUID uuid = UUID.randomUUID();

        // prepare normalized protobuf message
        SkytaleMessages.EncodeMessage encodeMessage = SkytaleMessages.EncodeMessage.newBuilder()
                .setUuid(uuid.toString())
                .setMessage(encodeMessageRequest.getMessage())
                .setSecret(encodeMessageRequest.getSecret())
                .build();

        // ship the message to SQS as JSON (using com.google.protobuf.util.JsonFormat)
        producer.clearAll() // because our producer is an autowired singleton
                .to("direct:encodeQueue")
                .withBody(JsonFormat.printer().print(encodeMessage))
                .send();

        return new ResponseEntity<>(new EncodeMessageResponse().uuid(uuid), HttpStatus.CREATED);
    }
}


