package no.hamre.springboot.openapi3.rest.controller

import no.hamre.springboot.openapi3.BookRepositoory
import no.hamre.springboot.openapi3.rest.model.Book
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
class BookController {

  @RequestMapping(path = ["/v1/books"], method = [RequestMethod.POST])
  fun addBook(@Valid @RequestBody book: Book): Book {
    BookRepositoory.addBook(book)
    return book
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