package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> list() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        Optional<UserEntity> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        Boolean isExist = userService.isExist(user.getUsername());
        if (isExist) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            userService.save(user);
            Optional<UserEntity> createdUser = userService.findByUsername(user.getUsername());
            if (createdUser.isPresent()) {
                return new ResponseEntity<>(createdUser.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<UserEntity> updateUsername(@RequestBody UserEntity user,
                                                     @PathVariable Integer id) {
        Boolean isExistById = userService.isExist(id);
        if (isExistById) {
            Boolean isExistByUsername = userService.isExist(user.getUsername());
            if (isExistByUsername) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                userService.updateUsername(id, user.getUsername());
                Optional<UserEntity> updatedUser = userService.findById(id);
                if (updatedUser.isPresent()) {
                    return new ResponseEntity<>(updatedUser.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
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
