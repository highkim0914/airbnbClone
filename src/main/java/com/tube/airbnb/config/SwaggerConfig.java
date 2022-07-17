package com.tube.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("airbnb")
                .description("API EXAMPLE")
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("example")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.tube.airbnb"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

}