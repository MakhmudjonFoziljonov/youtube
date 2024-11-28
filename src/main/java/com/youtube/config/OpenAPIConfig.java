package com.youtube.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Bormi",
                        email = "something@gmal.com",
                        url = "http://app.mazgi.uz/"
                ),
                description = "This API exposes endpoints to manage tutorials.",
                title = "Youtube Management API",
                version = "1.0",
                license = @License(
                        name = "Videohub",
                        url = "https://videohub.uz/"
                ),
                termsOfService = "Savol javob guruhi: https://t.me/code_uz_group"
        ),
        servers = {
                @io.swagger.v3.oas.annotations.servers.Server(
                        description = "DEV",
                        url = "http://api.mazgi.uz"
                ),
                @io.swagger.v3.oas.annotations.servers.Server(
                        description = "LOCAL",
                        url = "http://localhost:8080"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {
}

