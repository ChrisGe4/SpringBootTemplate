package com.db.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Chris.Ge
 */
@SpringBootApplication
public class DataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJdbcApplication.class, args);
        System.exit(0);

    }

    @Bean
    public CommandLineRunner exampleQuery(CarJdbcRepository repository) {
        return args -> repository.findByMakeIgnoringCase("honda").forEach(System.out::println);
    }
}
