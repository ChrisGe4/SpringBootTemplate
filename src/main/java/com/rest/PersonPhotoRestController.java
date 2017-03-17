package com.rest;

import com.db.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author Chris.Ge
 */
@RestController
@RequestMapping("/people/{id}/photo")
public class PersonPhotoRestController {

    private File root;

    @Autowired
    private PersonRepository personRepository;

    @Value("${user.home}")
    public void setUserHome(String home) {
        this.root = new File(home, "Desktop/images");
        Assert.isTrue(this.root.exists() || this.root.mkdirs(),
            "The path '" + this.root.getAbsolutePath() + "' must exist.");
    }



}
