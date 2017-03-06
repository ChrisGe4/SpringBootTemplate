package com.rest;

import com.pojo.User;
import com.pojo.UserDatastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chris.Ge
 */
@RestController
@RefreshScope
public class DatabaseRestController {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseRestController.class);

    private final UserDatastore users;
    @Value("${configuration.projectName}")
    private String projectName;

    public DatabaseRestController(UserDatastore users) {
        this.users = users;
    }

    @RequestMapping("/project-name")
    public String getProjectName() {
        return projectName;
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @RequestMapping(value = "/user/{userToFind}", method = RequestMethod.GET, headers = "Accept=application/json")
    //if you rename userString to userToFind, don't need the @s
    public User getUser(@PathVariable("userToFind") String userToFind,
        @RequestHeader(value = "Accept", defaultValue = "application/json", required = false)
            String key) {
        User candidate = users.getUser(Integer.parseInt(userToFind));
        if (candidate == null) {
            throw new UserNotFoundException(
                "Could not find a user corresponding to submitted identifier: " + userToFind);
        }
        return candidate;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody User newUser) {
        users.createUser(newUser);
        return newUser;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleBadRequest(HttpServletRequest request, Exception ex) throws Exception {

        logger.error(request.getRequestURL().toString(), ex);

        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
            throw ex;

        return ex.getMessage();
    }
}
