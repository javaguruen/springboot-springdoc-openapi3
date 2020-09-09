package no.hamre.springboot.openapi3.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class JacksonConfig {

  @Bean
  fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
    val mapper = ObjectMapperFactory.create()
    return MappingJackson2HttpMessageConverter(mapper)
  }
}