package com.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris.Ge
 */
@Service
public class SlowService {

    @Cacheable("slow")
    public String execute(String arg) {

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }

}
