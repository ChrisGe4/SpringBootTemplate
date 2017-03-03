package com.bootstrap;

import com.pojo.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
//there is a sys property for active profile as well or add it to yml spring.profiles.active=dev
//@ActiveProfiles("dev")
//@SpringBootTest
@SpringTestHelper
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DevVersionCreatBeansWithProfile.class)

//@import(config class)
public class DemoApplicationTests {


    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {

        //another way to set profile, can be used in command line
        /*
     * Set Active Profiles to dev
     *         System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		 */
        //get the context, like injector
        // this is an independent way to get context
        //in this example, using @ way
        //        try (final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        //            DevVersionCreatBeansWithProfile.class);) {
        //
        //            applicationContext.getBean(DataSource.class); //or use a name,  getBean("",xxx)
        //
        //        }

        applicationContext.getBean(Database.class); //or use a name,  getBean("",xxx)

    }

    //with profile, context can be refreshed, context.setprofile-> context.refresh()

}
