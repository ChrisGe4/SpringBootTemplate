package com.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Chris.Ge
 */
//automatically picked up
public interface CarRepository extends CrudRepository<Car, Long> {
    // @RestResource(path = "find")
    Iterable<Car> findByMakeIgnoringCase(@Param("make") String make);
}
