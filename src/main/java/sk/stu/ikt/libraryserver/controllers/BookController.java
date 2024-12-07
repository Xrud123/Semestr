package sk.stu.ikt.libraryserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.stu.ikt.libraryserver.entities.Book;
import sk.stu.ikt.libraryserver.services.BookService;

@RestController("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book createdBook = bookService.createBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdBook);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        Book book = bookService.getBook(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Book with ID " + id + " was deleted successfully.");
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Book> putBook(@PathVariable("id") Long id, @RequestBody Book updatedBook) {
        Book book = bookService.putBook(id, updatedBook);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }
}
