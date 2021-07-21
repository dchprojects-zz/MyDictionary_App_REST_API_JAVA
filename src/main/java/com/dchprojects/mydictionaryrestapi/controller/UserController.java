package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.User;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Boolean isExist = userService.isExist(user.getUsername());
        if (isExist) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            userService.save(user);
            User createdUser = userService.findByUsername(user.getUsername());
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<User> updateUsername(@RequestBody User user,
                                           @PathVariable Integer id) {
        Boolean isExistById = userService.isExist(id);
        if (isExistById) {
            Boolean isExistByUsername = userService.isExist(user.getUsername());
            if (isExistByUsername) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                userService.updateUsername(id, user.getUsername());
                User updatedUser = userService.findById(id);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Boolean isExist = userService.isExist(id);
        if (isExist) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
