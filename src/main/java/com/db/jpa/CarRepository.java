package com.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author Chris.Ge
 */
//automatically picked up
public interface CarRepository extends CrudRepository<Car, Long> {
    @RestResource(path = "find")
    Iterable<Car> findByMakeIgnoringCase(@Param("make") String make);

//    @RestResource(path = "find")
    //    Iterable<Car> findByColorIgnoringCase(@Param("color") String color);
}
