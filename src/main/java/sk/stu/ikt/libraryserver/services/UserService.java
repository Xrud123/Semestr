package sk.stu.ikt.libraryserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.stu.ikt.libraryserver.entities.User;
import sk.stu.ikt.libraryserver.repos.UserRepo;
import sk.stu.ikt.libraryserver.security.UserRole;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );
        return userRepo.save(user);
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User wasn't found!"));
    }

    public User deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " wasn't found!"));
        userRepo.delete(user);
        return user;
    }

    public User putUser(Long id, User updatedUser) {
        User existingUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User wasn't found!"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepo.save(existingUser);
    }
}

