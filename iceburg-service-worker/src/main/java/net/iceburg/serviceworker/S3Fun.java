package net.iceburg.serviceworker;

import software.amazon.awssdk.services.s3.S3Client;
import org.springframework.beans.factory.annotation.Autowired;

public class S3Fun {

	@Autowired
	private S3Client s3Client;

}
