package com.backgf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        final String securityScheme = "bearerAuth";

        return new OpenAPI()
            .info(new Info()
                .title("BackGF API")
                .version("v1")
                .description("Documentação da API do BackGF")
            )
            .addSecurityItem(new SecurityRequirement().addList(securityScheme))
            .components(new Components()
                .addSecuritySchemes(securityScheme,
                    new SecurityScheme()
                        .name(securityScheme)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("JWT Authorization header using the Bearer scheme"
                    )
                )
            );

    }
}