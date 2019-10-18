package com.Kh033Java.Kh033Java.travelbook.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Config contains all swagger-related configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger api configuration.
     *
     * @return Docket configured bean.
     */
    @Bean
    public Docket travelBookApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.com.Kh033Java.Kh033Java.travelbook.endpoints"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .groupName("travelbook endpoints")
                .apiInfo(apiInfo());
    }

    /**
     * Swagger information.
     *
     * @return ApiInfoBuilder swagger builder
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TravelBook Service")
                .description("TravelBook Rest API Documentation")
                .license("TravelBook Platform License")
                .version("1.0")
                .build();
    }
}
