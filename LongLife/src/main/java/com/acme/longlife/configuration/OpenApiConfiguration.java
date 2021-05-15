package com.acme.longlife.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfiguration {
    @Bean(name = "longlifeOpenApi")
    public OpenAPI longlifeOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("LongLife.io Application Api")
                .description("LongLife.io API implemented with Spring Boot RESTful service and documented using spingdoc_openapi and OpenAPI 3.0"));
    }

}
