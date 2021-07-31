package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.entity.role.Role;
import com.dchprojects.mydictionaryrestapi.entity.role.RoleName;
import com.dchprojects.mydictionaryrestapi.exception.AppException;
import com.dchprojects.mydictionaryrestapi.repository.RoleRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private WordService wordService;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    private PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    public List<UserEntity> list() {
        return userService.listAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        Optional<UserEntity> user = userService.findById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        Boolean isExist = userService.isExist(user.getNickname());
        if (isExist) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {

            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
            user.setRoles(roles);
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userService.save(user);

            Optional<UserEntity> createdUser = userService.findByNickname(user.getNickname());
            if (createdUser.isPresent()) {
                return new ResponseEntity<>(createdUser.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
    }

    @PutMapping("/{userId}/nickname")
    public ResponseEntity<UserEntity> updateNickname(@RequestBody UserEntity user,
                                                     @PathVariable Long userId) {
        Boolean isExistByUserId = userService.isExist(userId);
        if (isExistByUserId) {
            Boolean isExistByUsername = userService.isExist(user.getNickname());
            if (isExistByUsername) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                userService.updateNickname(userId, user.getNickname());
                Optional<UserEntity> updatedUser = userService.findById(userId);
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

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Boolean isExist = userService.isExist(userId);
        if (isExist) {
            userService.delete(userId);
            courseService.deleteAllByUserId(userId);
            wordService.deleteAllByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
