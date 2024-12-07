package sk.stu.ikt.libraryserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stu.ikt.libraryserver.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
