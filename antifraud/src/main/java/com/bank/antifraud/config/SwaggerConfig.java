package com.bank.antifraud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Конфигурация Swagger.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Anti-fraud System Api",
                description = "Anti-frau System", version = "1.0.0",
                contact = @Contact(
                        name = "Антонов Кирилл",
                        email = "kirill50027@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
