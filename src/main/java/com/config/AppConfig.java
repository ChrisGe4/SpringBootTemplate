package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chris.Ge
 */
@Component
@ConfigurationProperties("configuration")
//@PropertySource("classpath:xxxx")
public class AppConfig {

    private String projectName;



    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    //YamlPropertiesFactoryBean = new ...
    //yaml.setResource(new ClassPathResource(file...))
}
