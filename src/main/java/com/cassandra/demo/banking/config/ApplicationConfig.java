package com.cassandra.demo.banking.config;

import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories
public class ApplicationConfig extends AbstractCassandraConfiguration {

    Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    Environment env;

    @Override
    public String getContactPoints() {
        logger.info("Cassandra contact-points: " + env.getProperty("spring.cassandra.contact-points"));
        return env.getProperty("spring.cassandra.contact-points");
    }

    @Override
    protected String getKeyspaceName() {
        logger.info("Cassandra keyspace: {}", env.getProperty("spring.cassandra.keyspace-name"));
        return env.getProperty("spring.cassandra.keyspace-name");
    }

    protected Integer getCassandraPort() {
        logger.info("Cassandra Port: {}", env.getProperty("spring.cassandra.port"));
        return Integer.valueOf(env.getProperty("spring.cassandra.port"));
    }

    protected Duration getRequestTimeout() {
        logger.info("Cassandra Request Timeout: {}", env.getProperty("spring.cassandra.request.timeout"));
        return Duration.parse(env.getProperty("spring.cassandra.request.timeout"));
    }

    protected Duration getConnectTimeout() {
        logger.info("Cassandra Connection Timeout: {}", env.getProperty("spring.cassandra.connection.connect-timeout"));
        return Duration.parse(env.getProperty("spring.cassandra.connection.connect-timeout"));
    }

//    @Bean
//    public CqlSessionFactoryBean session() {
//        CqlSessionFactoryBean session = new CqlSessionFactoryBean();
//
//        List<InetSocketAddress> contactPoints = Collections.singletonList(new InetSocketAddress(getContactPoints(), getCassandraPort()));
//
//        session.setSessionBuilderConfigurer(config -> {
//            try {
//                return config
//                        .addContactPoints(contactPoints)
//                        .withKeyspace(getKeyspaceName())
//                        .withConfigLoader(
//                                DriverConfigLoader.programmaticBuilder()
////                                        .withClass(DefaultDriverOption.RETRY_POLICY_CLASS, CustomRetryPolicy.class)
//                                        .withDuration(DefaultDriverOption.REQUEST_TIMEOUT, getRequestTimeout())
//                                        .withDuration(DefaultDriverOption.CONNECTION_CONNECT_TIMEOUT, getConnectTimeout())
////                                        .withString(DefaultDriverOption.REQUEST_CONSISTENCY, consistencyLevel)
////                                        .withLong(DefaultDriverOption.CONNECTION_MAX_REQUESTS, maxRequests)
////                                        .withLong(DefaultDriverOption.CONNECTION_POOL_LOCAL_SIZE, poolLocalSize)
////                                        .withLong(DefaultDriverOption.CONNECTION_POOL_REMOTE_SIZE, poolRemotelSize)
////                                        .withInt(CustomOptions.READ_ATTEMPTS, numTentativasLeitura)
////                                        .withInt(CustomOptions.WRITE_ATTEMPTS, numTentativasEscrita)
////                                        .withInt(CustomOptions.UNAVAILABLE_ATTEMPTS, numTentativasIndisponivel)
////                                        .withInt(CustomOptions.REQUEST_ERROR_ATTEMPTS, numTentativasRequestError)
//                                        .build()
//                        )
//                        .withLocalDatacenter(policyDataCenter)
//                        .withMetricRegistry(meterRegistry);
//
//            } catch (Exception e) {
//                logger.error("Erro ao se conectar com o Keyspace");
//                throw new RuntimeException("Erro ao se conectar com o Keyspace", e);
//            }
//        });
//
//        session.setKeyspaceName(getKeyspaceName());
//        return session;
//    }
}
