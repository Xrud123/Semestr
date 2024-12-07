package sk.stu.ikt.libraryserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stu.ikt.libraryserver.entities.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}