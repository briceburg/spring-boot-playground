package net.iceburg.skytale.encodeservice.impl;

import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import net.iceburg.skytale.openapi.server.api.V1Api;
import net.iceburg.skytale.openapi.server.model.EncodeMessageRequest;
import net.iceburg.skytale.openapi.server.model.EncodeMessageResponse;
import net.iceburg.skytale.starter.SkytaleUUIDWriter;
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
    private SkytaleUUIDWriter skytaleUUIDWriter;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public ResponseEntity<EncodeMessageResponse> encodeMessage(EncodeMessageRequest encodeMessageRequest) throws Exception {
        UUID uuid = UUID.randomUUID();

        ObjectWriter writer = skytaleUUIDWriter.getUUIDWriter(encodeMessageRequest, uuid);
        producerTemplate.sendBody("direct:encodeQueue", writer.writeValueAsString(encodeMessageRequest));

        return new ResponseEntity<>(new EncodeMessageResponse().uuid(uuid), HttpStatus.CREATED);
    }
}


