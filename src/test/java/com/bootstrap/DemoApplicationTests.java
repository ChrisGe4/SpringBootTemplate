package com.bootstrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@ActiveProfiles()    there is a sys property for active profile as well


@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = javebean class)
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    //with profile, context can be refreshed, context.setprofile-> context.refresh()

}
