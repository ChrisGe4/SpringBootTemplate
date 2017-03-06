package com.rest;

import com.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * @author Chris.Ge
 */
public class RestClient {

    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    @Inject
    private RestTemplate restTemplate;
    @Inject
    //pay attention to the name
    private String baseUrl;


    public void getUser() {

        HttpEntity<String> entity = prepareGetRequestHeaders(MediaType.APPLICATION_JSON);
        ResponseEntity<User> response =
            restTemplate.exchange(baseUrl + "/user/1", HttpMethod.GET, entity, User.class);
        response.getStatusCode();
        response.getHeaders();

        User user = response.getBody();
        logger.info("*************************** {}: {}", user.getId(), user.getFullName());
    }

    public void createUser() {
        User newUser = new User();
        newUser.setCity("Sweet");
        newUser.setFullName("Butch Otter");
        newUser.setStreet("Main Way");
        newUser.setUserName("Butch");
        newUser.setZipcode("83400");
        HttpEntity<User> entity = new HttpEntity<>(newUser);

        ResponseEntity<User> response =
            restTemplate.postForEntity(baseUrl + "/user", entity, User.class);

        User u = response.getBody();
        logger.info("New user: {}, {}", u.getId(), u.getFullName());
    }

    public void removeUser() {
        restTemplate.delete(baseUrl + "/user/{id}", "6");
    }

    public void updateUser() {
        HttpEntity<String> entity = prepareGetRequestHeaders(MediaType.APPLICATION_JSON);

        ResponseEntity<User> response =
            restTemplate.exchange(baseUrl + "/user/1", HttpMethod.GET, entity, User.class);


        User updatedUser = response.getBody();
        updatedUser.setFullName("Crouch");

        HttpEntity<User> updated = new HttpEntity<>(updatedUser);

        restTemplate.put(baseUrl + "/user/{id}", updated, updatedUser.getId().toString());
    }
    private HttpEntity<String> prepareGetRequestHeaders(MediaType type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

}
