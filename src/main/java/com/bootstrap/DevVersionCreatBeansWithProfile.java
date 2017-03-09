package com.bootstrap;

import com.pojo.Database;
import com.pojo.UserDatastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Chris.Ge
 */

//profile-specific properties can also be defined using the naming convention application-{profile}.properties
@Profile("dev")
@Configuration //use to create bean, can be loaded via different approach
//@ComponentScan(basePackages = { "com.springclass.configuration" })
public class DevVersionCreatBeansWithProfile {

    @Value("${server.port}")
    private String port;

    //by default, its singleton
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//or use"prototype"
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySources() throws IOException {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);

        return pspc;
    }

    @Bean("devDataSource")
    public String dataSource() {

        System.out.println(" create data source ");
        return "datasource";
    }

    @Bean("users")
    public UserDatastore getUserDatasource() {
        return UserDatastore.getuserDatastore();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String baseUrl() {



        return String.format("http://localhost:{}", port);
    }

    @Bean
    public int number() {
        return 10;
    }

    //this is just an example, since we have profile, normally wont use this
    // use spring naming convention
    @Bean
    @Autowired
    @DependsOn({"propertySources"})//change the bean creation order
    public Database createDB(@Qualifier("devDataSource") final String ds, int number
        //number will be injected
    ) {
        System.out.println("create DB");
        return new Database(ds, number);
    }
}
