package com.freelance.ScrumStudy.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/bundles/**",
                "/Content/**",
                "/fonts/**",
                "/Images/**",
                "/PM-Images/**",
                "/Scripts/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/bundles/",
                        "classpath:/static/Content/",
                        "classpath:/static/fonts/",
                        "classpath:/static/images/",
                        "classpath:/static/PM-Images/",
                        "classpath:/static/Scripts/");
    }

}