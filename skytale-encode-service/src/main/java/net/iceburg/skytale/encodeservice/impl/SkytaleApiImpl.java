package net.iceburg.skytale.encodeservice.impl;

import net.iceburg.skytale.openapi.server.api.V1Api;
import net.iceburg.skytale.openapi.server.model.EncodeMessageResponse;
import net.iceburg.skytale.openapi.server.model.EncodeMessageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SkytaleApiImpl implements V1Api {

    @Override
    public ResponseEntity<EncodeMessageResponse> encodeMessage(EncodeMessageRequest encodeMessageRequest){
        return V1Api.super.encodeMessage(encodeMessageRequest);

    }
    // default ResponseEntity<EncodeMessageResponse> encodeMessage(@ApiParam(value = "" ,required=true )  @Valid @RequestBody EncodeMessageRequest encodeMessageRequest) {
}


