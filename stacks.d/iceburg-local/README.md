# iceburg-local stack

the purpose of this stack is to provide dependencies to iceburg services.

## components

* [local-kms](https://github.com/nsmithuk/local-kms) provides a mock AWS KMS endpoint
* [minio](https://min.io/) provides a mock AWS S3 endpoint including signed URLs
* [postgres](https://hub.docker.com/_/postgres) provides a local postgres database
* [redis](https://hub.docker.com/_/redis/) provides a local redis instance
* [proxy](https://hub.docker.com/_/haproxy/) acts as the amazon ALB, proxies to local services as
