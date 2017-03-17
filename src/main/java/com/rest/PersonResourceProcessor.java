package com.rest;

import com.db.jpa.Person;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * HATEOAS (Hypermedia as the Engine of Application State) is a constraint of the REST application architecture.
 * you will see something like:
 * {
 * "name": "Alice",
 * "links": [ {
 * "rel": "photo",
 * "href": "http://localhost:8080//people/1/photo"
 * } ]
 * }
 * this is related to spring data
 *
 * @author Chris.Ge
 */
@Component
public class PersonResourceProcessor implements ResourceProcessor<Resource<Person>> {
    @Override
    public Resource<Person> process(Resource<Person> resource) {
        String id = Long.toString(resource.getContent().getId());
        UriComponents uriComponent =
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/people/{id}/photo")
                .buildAndExpand(id);
        String uri = uriComponent.toUriString();
        resource.add(new Link(uri, "photo"));
        return resource;
    }
}
