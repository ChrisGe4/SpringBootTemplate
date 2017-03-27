package com.rest;

/**
 * @author Chris.Ge
 */
public class PersonNotFoundException extends RuntimeException {

    private Long personId;

    public PersonNotFoundException(Long personId) {
        super("person# " + personId + " was not found");
        this.personId = personId;
    }

    public Long getPersonId() {
        return personId;
    }
}
