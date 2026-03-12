# AWS Keyspaces Spring Boot 3 POC

Example Spring Boot 3 project configured for **AWS Keyspaces** using:

- Spring Data Cassandra
- DataStax Java Driver v4
- AWS SigV4 auth plugin
- TLS with truststore

## Key files

- `src/main/java/com/example/keyspaces/config/CassandraConfig.java`
- `src/main/resources/application.conf`
- `src/main/resources/application.yml`

## Run

1. Create a PKCS12 truststore containing Amazon Trust Services cert chain.
2. Update `aws.keyspaces.*` in `application.yml` or override with environment variables.
3. Ensure AWS credentials are available (environment, profile, role, etc.).
4. Start app:

```bash
./mvnw spring-boot:run
```
