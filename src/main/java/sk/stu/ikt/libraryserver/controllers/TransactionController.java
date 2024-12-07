package sk.stu.ikt.libraryserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.stu.ikt.libraryserver.entities.Book;
import sk.stu.ikt.libraryserver.entities.Transaction;
import sk.stu.ikt.libraryserver.entities.User;
import sk.stu.ikt.libraryserver.services.BookService;
import sk.stu.ikt.libraryserver.services.TransactionService;
import sk.stu.ikt.libraryserver.services.UserService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    // Create a new transaction
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/")
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam("userId") Long userId,
            @RequestParam("bookId") Long bookId,
            @RequestParam("action") String action) {

        User currentUser = userService.getUser(userId);
        Book currentBook = bookService.getBook(bookId);
        Transaction transaction = transactionService.createTransaction(currentUser, currentBook, action);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transaction);
    }

    // Update an existing transaction
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> putTransaction(
            @PathVariable("id") Long transactionId,
            @RequestParam("action") String newAction) {

        Transaction transaction = transactionService.putTransaction(transactionId, newAction);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transaction);
    }

    // Get a transaction by ID
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") Long transactionId) {
        Transaction transaction = transactionService.getTransaction(transactionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transaction);
    }


    // Delete a transaction by ID
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long transactionId) {
        transactionService.deleteTransaction(transactionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Transaction with ID: " + transactionId + " was deleted successfully.");
    }
}

