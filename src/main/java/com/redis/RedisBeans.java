package com.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Chris.Ge
 */
@Configuration
public class RedisBeans {

    //without this will use memory
    //it is recommended to change this (by setting 'usePrefix' to 'true')  call setUsePrefix(true). then you can use
    //name space in @cacheable
    //need to @EnableCaching in main app
    @Bean
    RedisCacheManager cacheManager(StringRedisTemplate template) {
        RedisCacheManager manager = new RedisCacheManager(template);
        manager.setUsePrefix(true);
        return manager;
    }

}
