package no.hamre.springboot.openapi3

import no.hamre.springboot.openapi3.rest.model.Author
import no.hamre.springboot.openapi3.rest.model.Book

object BookRepositoory {
  private val authors = mutableListOf(
      Author(id = 1, firstName = "Stephen", lastName = "King"),
      Author(id = 2, firstName = "Tom", lastName = "Clancy")
  )
  private val books = mutableListOf(
      Book(id = 1, title = "Pet Semetary", authors = listOf(authors[0])),
      Book(id = 1, title = "The Shining", authors = listOf(authors[0])),
      Book(id = 1, title = "The hunt for Red October", authors = listOf(authors[1]))
  )

  fun addBook(book: Book) {
    books.add(book)
  }

  fun updateBook(book: Book) {
    books.removeIf { it.id == book.id }
    books.add(book)
  }

  fun getBook(id: Long): Book? {
    return books.firstOrNull { it.id == id }
  }

  fun deleteBook(id: Long) {
    books.removeIf { it.id == id }
  }

  fun allBooks(): List<Book> {
    return books
  }
}