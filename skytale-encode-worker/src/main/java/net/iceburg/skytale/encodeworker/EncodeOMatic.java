package net.iceburg.skytale.encodeworker;

import net.iceburg.skytale.protobuf.SkytaleMessages;
import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.util.json.Jsonable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.http.ContentStreamProvider;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.EncryptRequest;
import software.amazon.awssdk.services.kms.model.EncryptResponse;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;

@Component
public class EncodeOMatic {
    @Autowired
    private KmsClient kmsClient;

    @Autowired
    private SkytaleProperties skytaleProperties;

    public byte[] encodeMessage(SkytaleMessages.EncodeMessage encodeMessage){

        EncryptRequest encryptRequest = EncryptRequest.builder()
                .keyId(skytaleProperties.kmsKeyId)
                .plaintext(SdkBytes.fromByteArray(encodeMessage.getSecretBytes().toByteArray()))
                .encryptionContext(Collections.singletonMap("secret", encodeMessage.getSecret()))
                .build();

        EncryptResponse encryptResponse = kmsClient.encrypt(encryptRequest);
        return encryptResponse.ciphertextBlob().asByteArray();
    }
}
