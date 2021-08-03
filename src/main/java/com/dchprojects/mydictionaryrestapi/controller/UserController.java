package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateNicknameRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.Role;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleName;
import com.dchprojects.mydictionaryrestapi.repository.RoleRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

    @PutMapping("/{userId}/nickname")
    public ResponseEntity<UserEntity> updateNickname(@RequestBody UpdateNicknameRequest user,
                                                     @PathVariable Long userId) {
        return new ResponseEntity<>(userService.updateNickname(userId, user.getNickname()), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
