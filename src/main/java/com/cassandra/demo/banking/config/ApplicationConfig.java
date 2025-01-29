package com.cassandra.demo.banking.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.time.Duration;


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
//    public CassandraOperations cassandraTemplate(CqlSession session) {
//        // Usando o CqlSession injetado diretamente para criar o CassandraTemplate
//        return new CassandraTemplate(session);
//    }
}
