package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.Role;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleName;
import com.dchprojects.mydictionaryrestapi.entity_converter.EntityConverter;
import com.dchprojects.mydictionaryrestapi.repository.RoleRepository;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public List<UserResponse> listAll() {
        return userRepository.findAll()
                .stream()
                .map(EntityConverter::userEntityToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long userId) {
        Boolean userExists = userRepository.existsByUserId(userId);
        if (userExists) {
            return EntityConverter.userEntityToUserResponse(userRepository.findById(userId).get());
        } else {
            throw new NoSuchElementException("User not found");
        }
    }

    @Override
    public UserResponse findByNickname(String nickname) {
        Boolean userExists = userRepository.existsByNickname(nickname);
        if (userExists) {
            return EntityConverter.userEntityToUserResponse(userRepository.findByNickname(nickname).get());
        } else {
            throw new NoSuchElementException("User not found!");
        }
    }

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
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

            return EntityConverter.userEntityToUserResponse(userRepository.save(newUser));
        }
    }

    @Override
    public UserResponse createAdmin(CreateUserRequest createUserRequest) {
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

            return EntityConverter.userEntityToUserResponse(userRepository.save(newUser));
        }
    }

}
