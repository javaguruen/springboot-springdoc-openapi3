package no.hamre.springboot.openapi3.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

object ObjectMapperFactory {
  fun create(): ObjectMapper {
    return configure(ObjectMapper())
  }

  fun configure(mapper: ObjectMapper): ObjectMapper {
    mapper.registerModule(KotlinModule())
    return mapper
  }
}