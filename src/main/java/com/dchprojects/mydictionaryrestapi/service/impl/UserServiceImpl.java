package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateNicknameRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.Role;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleName;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.repository.RoleRepository;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

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

    @Override
    public UserEntity updateNickname(Long userId, UpdateNicknameRequest updateNicknameRequest) {
        Boolean existsByUserId = userRepository.existsByUserId(userId);
        if (existsByUserId) {
            Boolean existsByNickname = userRepository.existsByNickname(updateNicknameRequest.getNickname());
            if (existsByNickname) {
                throw new ValidationException("Nickname exists!");
            } else {
                UserEntity userForUpdate = userRepository.findById(userId).get();

                userForUpdate.setNickname(updateNicknameRequest.getNickname());

                UserEntity updatedUser = userRepository.save(userForUpdate);
                return updatedUser;
            }
        } else {
            throw new UsernameNotFoundException(format("User with nickname - %s, not found", updateNicknameRequest.getNickname()));
        }
    }

    @Override
    public void delete(Long userId) {
        Boolean existsByUserId = userRepository.existsByUserId(userId);
        if (existsByUserId) {
            courseRepository.deleteAllByUserId(userId);
            wordRepository.deleteAllByUserId(userId);
            userRepository.deleteById(userId);
        } else {
            throw new UsernameNotFoundException(format("User with userId - %d, not found", userId));
        }
    }

}
