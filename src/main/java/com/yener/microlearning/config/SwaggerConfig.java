package com.yener.microlearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.yener.microlearning.controller"))
                .paths(PathSelectors.ant("/*/**")).build().apiInfo(apiInfo())//;
                .securitySchemes(Collections.singletonList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("MICRO LEARNING REST API",
                "This API developed for MAPSAPI TEAM", "By MAPS API Team",
                "There is no terms of service", "yenerhan@gmail.com", "License of API", "API license URL");
        return apiInfo;
    }
}