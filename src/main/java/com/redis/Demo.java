package com.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Chris.Ge
 */
@Component
public class Demo implements CommandLineRunner {
    private static Log logger = LogFactory.getLog(Demo.class);
    private final StringRedisTemplate template;
    private final SlowService service;

    @Autowired
    public Demo(StringRedisTemplate template, SlowService slowService) {
        this.template = template;
        this.service = slowService;
    }

    @Override
    public void run(String... args) throws Exception {
        reset();
        operations();
        javaTypes();
        caching();
    }

    private void reset() {
        this.template.delete(Arrays.asList("abc", "boot", "slow~keys", "data"));
    }

    /**
     * The `operations()` method show how you can work with low level Redis operations. This
     * example increments a value by 1, 2, 3 then 6.
     */
    private void operations() {
        ValueOperations<String, String> ops = this.template.opsForValue();
        Arrays.asList(1, 2, 3, 4).forEach(i -> ops.increment("abc", i));
        logger.info(ops.get("abc"));
    }

    /*
    The `javaTypes()` method shows how you can map Redis structures to Java types. Notice how
    when we recreate the map we _don't_ get an empty structure, the previously inserted values
    are immediately available.

     */
    private void javaTypes() {
        Map<String, String> map = new DefaultRedisMap<String, String>("data", this.template);
        map.put("spring", "boot");
        //will ge the previous instance
        map = new DefaultRedisMap<String, String>("data", this.template);
        logger.info(map.get("spring"));


    }

    private void caching() {
        logger.info("----> 1 " + this.service.execute("boot"));
        logger.info("----> 2 " + this.service.execute("boot"));
        logger.info("----> 3 " + this.service.execute("boot"));
    }
}
