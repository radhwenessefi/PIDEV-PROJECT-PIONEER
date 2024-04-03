package tn.esprit.projectbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.util.Collections;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("your.package.name"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .produces(Collections.singleton(MediaType.APPLICATION_OCTET_STREAM_VALUE));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Your API Title")
                .description("Your API Description")
                .version("1.0")
                .build();
    }

}
