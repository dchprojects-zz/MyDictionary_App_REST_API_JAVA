package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.Role;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleName;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.repository.RoleRepository;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;
    private final WordRepository wordRepository;

    @Override
    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long userId) {
        Boolean userExists = userRepository.existsByUserId(userId);
        if (userExists) {
            return userRepository.findById(userId).get();
        } else {
            throw new NoSuchElementException("User not found");
        }
    }

    @Override
    public UserEntity findByNickname(String nickname) {
        Boolean userExists = userRepository.existsByNickname(nickname);
        if (userExists) {
            return userRepository.findByNickname(nickname).get();
        } else {
            throw new NoSuchElementException("User not found!");
        }
    }

    @Override
    public UserEntity createUser(CreateUserRequest createUserRequest) {
        Boolean existsByNickname = userRepository.existsByNickname(createUserRequest.getNickname());
        if (existsByNickname) {
            throw new ValidationException("Nickname exists!");
        } else {
            UserEntity newUser = new UserEntity();
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());

            newUser.setNickname(createUserRequest.getNickname());
            newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            newUser.setRoles(roles);

            UserEntity createdUser = userRepository.save(newUser);
            return createdUser;
        }
    }

    @Override
    public UserEntity createAdmin(CreateUserRequest createUserRequest) {
        Boolean existsByNickname = userRepository.existsByNickname(createUserRequest.getNickname());
        if (existsByNickname) {
            throw new ValidationException("Nickname exists!");
        } else {
            UserEntity newUser = new UserEntity();
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());

            newUser.setNickname(createUserRequest.getNickname());
            newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            newUser.setRoles(roles);

            UserEntity createdUser = userRepository.save(newUser);
            return createdUser;
        }
    }

}
