package no.hamre.springboot.openapi3.rest.model

data class Book(
    val id: Long?,
    val title: String,
    val authors: List<Author>
)

data class Author(
    val id: Long?,
    val firstName: String,
    val lastName: String
)

