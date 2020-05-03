package net.iceburg.skytale.decodeworker;

import net.iceburg.skytale.protobuf.SkytaleMessages;
import net.iceburg.skytale.starter.config.SkytaleProperties;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DecryptRequest;
import software.amazon.awssdk.services.kms.model.DecryptResponse;
import software.amazon.awssdk.services.kms.model.EncryptRequest;
import software.amazon.awssdk.services.kms.model.EncryptResponse;
import software.amazon.awssdk.services.kms.transform.DecryptRequestMarshaller;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Request;

import java.io.OutputStream;
import java.util.Collections;

@Component
public class DecodeOMatic {
    @Autowired
    private KmsClient kmsClient;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private SkytaleProperties skytaleProperties;

    public byte[] decodeMessage(SkytaleMessages.DecodeMessage decodeMessage){

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(skytaleProperties.bucketNames.secrets)
                .key(decodeMessage.getEncodeMessageUuid().toString())
                .build();

        // fetch the encoded message bytes from S3
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);


        // decode the bytes
        DecryptRequest decryptRequest = DecryptRequest.builder()
                .keyId(skytaleProperties.kmsKeyId)
                .encryptionContext(Collections.singletonMap("secret", decodeMessage.getSecret()))
                .ciphertextBlob(SdkBytes.fromByteBuffer(responseBytes.asByteBuffer()))
                .build();

        DecryptResponse decryptResponse = kmsClient.decrypt(decryptRequest);

        return decryptResponse.plaintext().asByteArray();
    }
}
