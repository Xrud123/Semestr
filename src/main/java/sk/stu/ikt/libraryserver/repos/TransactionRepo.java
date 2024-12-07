package sk.stu.ikt.libraryserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stu.ikt.libraryserver.entities.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}