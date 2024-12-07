package sk.stu.ikt.libraryserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stu.ikt.libraryserver.entities.Book;
import sk.stu.ikt.libraryserver.entities.Transaction;
import sk.stu.ikt.libraryserver.entities.User;
import sk.stu.ikt.libraryserver.repos.TransactionRepo;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction createTransaction(User currentUser, Book currentBook, String action) {
        Transaction transaction = new Transaction();
        transaction.setUser(currentUser);
        transaction.setBook(currentBook);
        transaction.setAction(action);
        transaction.setDate(new java.util.Date());
        return transactionRepo.save(transaction);
    }

    public Transaction putTransaction(Long transactionId, String newAction) {
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        transaction.setAction(newAction);

        return transactionRepo.save(transaction);
    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
    }

    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        transactionRepo.delete(transaction);
    }
}