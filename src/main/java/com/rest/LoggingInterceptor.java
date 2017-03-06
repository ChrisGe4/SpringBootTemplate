package com.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;

/**
 * @author Chris.Ge
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        logger.info("preHandle:: preHandle Execution for URI: {}", request.getRequestURI());

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            logger.info("--parameter name: {}", parameterNames.nextElement());
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.debug("--request header name: {}, value: {}", headerName,
                request.getHeader(headerName));
        }

        logger.info("preHandle:: handler Class: {}", handler.getClass().getName());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.info("postHandle executed--- view name: {} ", modelAndView.getViewName());
        }

        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            logger.debug("--response header name: {}, value: {}", headerName,
                response.getHeader(headerName));
        }

        logger.info("postHandle:: handler Class: {}", handler.getClass().getName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion:: executed---");

        logger.info("afterCompletion:: handler Class: {}", handler.getClass().getName());
        logger.info("afterCompletion:: response code---" + response.getStatus());

        if (ex != null) {
            logger.error("afterCompletion:: Exception Class: {}, {}", ex.getClass().getName(),
                ex.getMessage(), ex);
        }
    }
}
