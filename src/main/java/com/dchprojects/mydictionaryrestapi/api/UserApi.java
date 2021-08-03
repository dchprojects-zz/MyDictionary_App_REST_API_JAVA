package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateNicknameRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> list() {
        return userService.listAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        Boolean existsByUserId = userService.existsByUserId(userId);
        if (existsByUserId) {
            return new ResponseEntity<>(userService.findById(userId).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/nickname")
    public ResponseEntity<UserEntity> updateNickname(@RequestBody UpdateNicknameRequest updateNicknameRequest,
                                                     @PathVariable Long userId) {
        try {
            UserEntity updatedUser = userService.updateNickname(userId, updateNicknameRequest);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
