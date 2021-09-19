package com.example.webapp.controllers;

import com.example.webapp.exceptionHandling.NoSuchUsersException;
import com.example.webapp.models.Role;
import com.example.webapp.models.User;
import com.example.webapp.service.RoleService;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserService userService;
    private final RoleService roleService;

//    @Autowired
    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> showAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getPrincipalUser() {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(thisUser, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showOneUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new NoSuchUsersException("User with id " + id + " not found in Database");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        userService.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new NoSuchUsersException("User with id " + id + " not found in Database");
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}