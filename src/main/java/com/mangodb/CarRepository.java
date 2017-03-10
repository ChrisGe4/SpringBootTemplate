package com.mangodb;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Chris.Ge
 */
//automatically picked up
public interface CarRepository extends MongoRepository<Car, Long> {
    Iterable<Car> findByMakeIgnoringCase(String make);

    GeoResults<Car> findByPositionNear(Point p, Distance d);
}
