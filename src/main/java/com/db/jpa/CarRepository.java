package com.db.jpa;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Chris.Ge
 */
//automatically picked up
public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findByMakeIgnoringCase(String make);
}
