package sk.stu.ikt.libraryserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stu.ikt.libraryserver.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User, Long> {

    Optional<User> findUserByUsername(String username);
}
