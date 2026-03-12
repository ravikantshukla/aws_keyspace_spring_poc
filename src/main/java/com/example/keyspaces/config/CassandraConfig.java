package com.example.keyspaces.config;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import software.aws.mcs.auth.SigV4AuthProvider;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;

@Configuration
@EnableCassandraRepositories(basePackages = "com.example.keyspaces.repository")
@EnableConfigurationProperties(KeyspacesProperties.class)
public class CassandraConfig {

    @Bean
    public CqlSessionBuilderCustomizer keyspacesSessionCustomizer(KeyspacesProperties properties) {
        return (CqlSessionBuilder builder) -> builder
                .addContactPoint(new InetSocketAddress(properties.getContactPoint(), properties.getPort()))
                .withLocalDatacenter(properties.getLocalDatacenter())
                .withKeyspace(properties.getKeyspaceName())
                .withConfigLoader(DriverConfigLoader.fromClasspath("application.conf"))
                .withAuthProvider(new SigV4AuthProvider(properties.getRegion()))
                .withSslContext(buildSslContext(properties));
    }

    private SSLContext buildSslContext(KeyspacesProperties properties) {
        try {
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            try (InputStream inputStream = new FileInputStream(properties.getTruststorePath())) {
                trustStore.load(inputStream, properties.getTruststorePassword().toCharArray());
            }

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
            return sslContext;
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to initialize Cassandra SSL context", ex);
        }
    }
}
