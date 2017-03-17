package com.db.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chris.Ge
 */
@Configuration
public class Demo {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {

//            Arrays.asList("chris", "tom").forEach(name -> personRepository
            //                .save(new Person(name, (name + "@email.com").toLowerCase())));
            personRepository.findAll().forEach(System.err::println);
        };

    }

}
