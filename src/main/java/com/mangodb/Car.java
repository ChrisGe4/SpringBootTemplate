package com.mangodb;


import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

/**
 * @author Chris.Ge
 */

//changed
/*
The same domain to the JPA sample is used with this demo. Rather than being a `@Entity`
the `Car` here is a `@Document`. It has also been updated with a `position` (the
geo-location where the car is stored).
 */
@Document
public class Car {
    @org.springframework.data.annotation.Id
    private BigInteger id;

    private String make;

    private String model;

    private int year;
    @GeoSpatialIndexed(name = "position")
    private Point position;

    Car() {
    }

    public Car(String make, String model, int year, Point position) {
        super();
        this.make = make;
        this.model = model;
        this.year = year;
        this.position = position;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }


    @Override
    public String toString() {
        return make + " " + model + " " + year;
    }
}
