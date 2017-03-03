package com.rest;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;

/**
 * @author Chris.Ge
 */
public class AddHandlers extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(false).favorPathExtension(true)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaTypes(new HashMap<String, MediaType>() {
                {
                    put("xml", MediaType.APPLICATION_XML);
                    put("json", MediaType.APPLICATION_JSON);

                }
            });

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
    }
}
