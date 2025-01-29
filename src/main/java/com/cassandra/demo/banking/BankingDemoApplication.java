package com.cassandra.demo.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;


@SpringBootApplication
//@EnableCassandraAuditing
public class BankingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingDemoApplication.class, args);
	}
}
