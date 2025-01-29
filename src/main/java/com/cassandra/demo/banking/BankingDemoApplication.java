package com.cassandra.demo.banking;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

@SpringBootApplication
@EnableCassandraAuditing
public class BankingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingDemoApplication.class, args);
	}
}
