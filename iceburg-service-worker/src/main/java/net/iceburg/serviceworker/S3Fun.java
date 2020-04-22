package net.iceburg.serviceworker;

import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.s3.S3Client;

public class S3Fun {

  @Autowired private S3Client s3Client;
}
