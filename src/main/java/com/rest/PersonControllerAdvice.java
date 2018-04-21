package com.rest;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * @author Chris.Ge
 */
@ControllerAdvice // can assign pkg
@RequestMapping(produces = "application/vnd.error")
@ResponseBody
public class PersonControllerAdvice {


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public VndErrors fileNotFoundException(FileNotFoundException ex) {
        return this.error(ex, ex.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    public VndErrors personNotFoundException(PersonNotFoundException ex) {
        return this.error(ex, ex.getPersonId() + "");
    }


    private <E extends Exception> VndErrors error(E e, String logref) {
        String msg = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        return new VndErrors(logref, msg);
    }

}
