package com.broadcom.UI;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{spring:[a-zA-Z\\-]+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{spring:[a-zA-Z\\-]+}/**{spring:[a-zA-Z\\-]+}")
                .setViewName("forward:/index.html");
    }
}
