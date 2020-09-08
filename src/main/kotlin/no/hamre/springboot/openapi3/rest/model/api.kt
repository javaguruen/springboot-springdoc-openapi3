package no.hamre.springboot.openapi3.rest.model

import javax.validation.constraints.NotNull

data class Book(
    val id: Long?,
    @field:NotNull(message = "title is required")
    val title: String,
    @field:NotNull(message = "authors is required")
    val authors: List<Author>,
    val isbn: String? = null
)

data class Author(
    val id: Long?,
    val firstName: String,
    val lastName: String
)

