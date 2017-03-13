package com;

import com.config.AppConfig;
import com.db.jpa.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
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

    /**
     * JPA Example
     */
    //use this for startup.
    //now done by flyway
    //    @Bean
    //    public InitializingBean seedDatabase(CarEsRepository repository) {
    //        return () -> {
    //            repository.save(new Car("Honda", "Civic", 1997));
    //            repository.save(new Car("Honda", "Accord", 2003));
    //            repository.save(new Car("Ford", "Escort", 1985));
    //        };
    //    }

    //lasting happen
    @Bean
    public CommandLineRunner exampleQuery(CarRepository repository) {
        return args -> repository.findByMakeIgnoringCase("honda").forEach(System.err::println);
    }

}
