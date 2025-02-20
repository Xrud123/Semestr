package sk.stu.ikt.libraryserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.stu.ikt.libraryserver.entities.User;
import sk.stu.ikt.libraryserver.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return ResponseEntity
                .status(200)
                .body(user);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User with ID " + id + " was deleted successfully.");
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable("id") Long id,
                                        @RequestBody User updatedUser){
        User user = userService.putUser(id, updatedUser);
        return ResponseEntity
                .status(200)
                .body(user);

    }



}