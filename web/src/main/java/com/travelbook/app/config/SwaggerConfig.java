package com.travelbook.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger2 Configuration Class
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.travelbook.app"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Travel Book API",
                "Web and Mobile App Development Course API",
                "1.0",
                "Terms of Service",
                new Contact("Team 05", "https://gitlab.informatik.hs-fulda.de/fdai6766/travel-book", ""),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );
    }
}
