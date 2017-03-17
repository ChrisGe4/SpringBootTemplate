package com.rest;

import com.db.jpa.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * @author Chris.Ge
 */
@Configuration
public class SimpleRepositoryRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

    /*
    ///example/////
     config.addResourceMappingForDomainType(Person.class)
          .addResourceMappingFor("lastName")
          .setPath("surname"); // Change 'lastName' to 'surname' in the JSON
    config.addResourceMappingForDomainType(Person.class)
          .addResourceMappingFor("siblings")
          .setRel("siblings")
          .setPath("siblings"); // Pointless in this example,
                                // but shows how to change 'rel' and 'path' values.
     */

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Person.class);
    }
}
