package com.theodoro.supermarket_service.infra.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "theodoro",
                        email = "carlos.theodoro.damasceno@gmail.com",
                        url = "https://github.com/CATheodoro"
                ),
                description = "OpenApi documentation for Supermarket Service",
                title = "Supermarket Service",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://github.com/CATheodoro"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8081/"
                ),
        }
)
public class OpenApiConfig {}