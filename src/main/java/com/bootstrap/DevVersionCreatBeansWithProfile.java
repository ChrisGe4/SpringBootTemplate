package com.bootstrap;

import com.pojo.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Chris.Ge
 */

//profile-specific properties can also be defined using the naming convention application-{profile}.properties
@Profile("dev")
@Configuration //use to create bean, can be loaded via different approach
//@ComponentScan(basePackages = { "com.springclass.configuration" })
public class DevVersionCreatBeansWithProfile {

    //by default, its singleton
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//or use"prototype"
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySources() throws IOException {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);

        return pspc;
    }

    @Bean("devDataSource")
    public DataSource dataSource() {

        System.out.println(" create data source ");
        return null;
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
    public Database createDB(@Qualifier("devDataSource") final DataSource ds, int number
        //number will be injected
    ) {
        System.out.println("create DB");
        return new Database(ds, number);
    }
}