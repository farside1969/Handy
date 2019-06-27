package com.handy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//mapping that allows routing to opening page - spring magic
@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

//creates bean to allow Autowiring
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

//routes to AuthenticationInterceptor for login processing
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( authenticationInterceptor() );
    }
}
