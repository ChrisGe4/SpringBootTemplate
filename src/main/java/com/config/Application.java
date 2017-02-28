package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

/**
 * @author Chris.Ge
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Value("${configuration.projectName}")
    void setProjectName(String projectName) {
        System.out.println("projectName = " + projectName);
    }

    @Autowired
    void setEnv(Environment env) {
        System.out.println("env.getProperty(\"configuration.projectName\") = " + env
            .getProperty("configuration.projectName"));
    }

    @Autowired
    void setAppConfig(AppConfig config) {
        System.out.println("config.getProjectName() = " + config.getProjectName());
    }


    
}
