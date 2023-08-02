package com.dsen.upcycling.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Basic Auth",
        description = "Get a XAuth-Token for access to all APIs",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Upcycling")
                        .description("Created by DSEN to Medium Tutorials")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Medium Documentation")
                        .url("https://medium.com/@dileksen3417/swagger-ui-nedir-2e2a4e5dc882"));
    }

}
