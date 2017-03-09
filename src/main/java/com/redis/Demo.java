package com.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private void operations() {
        ValueOperations<String, String> ops = this.template.opsForValue();
        Arrays.asList(1, 2, 3, 4).forEach(i -> ops.increment("abc", i));
        logger.info(ops.get("abc"));
    }

    private void javaTypes() {
        Map<String, String> map = new DefaultRedisMap<String, String>("data", this.template);
        map.put("spring", "boot");
        map = new DefaultRedisMap<String, String>("data", this.template);
        logger.info(map.get("spring"));


    }

    private void caching() {
        logger.info("----> 1 " + this.service.execute("boot"));
        logger.info("----> 2 " + this.service.execute("boot"));
        logger.info("----> 3 " + this.service.execute("boot"));
    }
}
