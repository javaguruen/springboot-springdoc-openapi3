package no.hamre.springboot.openapi3

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Openapi3Application

fun main(args: Array<String>) {
	runApplication<Openapi3Application>(*args)
}

@Bean
fun customOpenAPI(@Value("\${application-description}") appDesciption: String , @Value("\${application-version}") appVersion: String ): OpenAPI {
	return OpenAPI()
			.info(Info()
							.title("sample application API")
							.version(appVersion)
							.description(appDesciption)
							.termsOfService("http://swagger.io/terms/")
							.license(License().name("Apache 2.0").url("http://springdoc.org")));
}