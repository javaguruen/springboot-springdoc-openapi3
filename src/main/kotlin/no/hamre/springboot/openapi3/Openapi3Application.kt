package no.hamre.springboot.openapi3

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
    info = Info(title = "Open API 3.0 example app."),
    servers = [
      Server(url = "http://localhost:8080/", description = "base url", )
    ],
    security = [
      SecurityRequirement(name = "basicAuth"),
      SecurityRequirement(name = "bearerToken")
    ]
)
@SecuritySchemes(
    SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
    ),
    SecurityScheme(
        name = "bearerToken",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
    )
)
class Openapi3Application

fun main(args: Array<String>) {

  runApplication<Openapi3Application>(*args)
}
