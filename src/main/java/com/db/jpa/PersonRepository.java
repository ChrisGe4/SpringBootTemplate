package com.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * @author Chris.Ge
 */
//automatically picked up
@RepositoryRestResource(path = "people")
public interface PersonRepository extends CrudRepository<Person, Long> {

    Collection<Person> findByEmail(@Param("email") String e);
}
