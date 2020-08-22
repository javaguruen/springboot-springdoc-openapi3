package no.hamre.springboot.openapi3.rest.controller

import no.hamre.springboot.openapi3.openapi3.BookRepositoory
import no.hamre.springboot.openapi3.rest.model.Book
import org.springframework.stereotype.Controller

@Controller
class BookController {

  fun addBook(book: Book): Unit {
    BookRepositoory.addBook(book)
  }

  fun updateBook(book: Book): Unit {
    BookRepositoory.updateBook(book)
  }

  fun deleteBook(id: Long): Unit {
    BookRepositoory.deleteBook(id)
  }

  fun getBook(id: Long): Unit {
    BookRepositoory.getBook(id)
  }

  fun getAllBooks(): List<Book> {
    return BookRepositoory.allBooks()
  }
}