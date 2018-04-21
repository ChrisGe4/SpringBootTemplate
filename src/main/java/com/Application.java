package com;

import com.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;

/**
 * @author Chris.Ge
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching
public class Application {

    public static void main(String[] args) {

        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        System.setProperty("management.security.enabled", "false");

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
