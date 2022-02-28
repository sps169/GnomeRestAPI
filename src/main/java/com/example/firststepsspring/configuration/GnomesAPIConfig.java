package com.example.firststepsspring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GnomesAPIConfig {
    @Bean
    public WebMvcConfigurer corsConfiguration() {


        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //version megaaccesible 0% seguro (recomendado para desarrollo)
                //registry.addMapping("/**");

                //version compleja
                registry.addMapping("/gnomes/**")
                        .allowedOrigins("http://localhost:9001", "http://localhost:9002")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
                registry.addMapping("/sweet/**")
                        .allowedOrigins("http://localhost:9001", "http://localhost:9002")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }
        };
    }
}
