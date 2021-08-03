package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateNicknameRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.Role;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleName;
import com.dchprojects.mydictionaryrestapi.repository.RoleRepository;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CourseService courseService;
    private final WordService wordService;

    @Override
    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean existsByNickname(String nickname) {  return userRepository.existsByNickname(nickname); }

    @Override
    public Boolean existsByUserId(Long userId) { return userRepository.existsByUserId(userId); }

    @Override
    public Optional<UserEntity> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserEntity createUser(CreateUserRequest createUserRequest) {
        Boolean existsByNickname = userRepository.existsByNickname(createUserRequest.getNickname());
        if (existsByNickname) {
            throw new ValidationException("Nickname exists!");
        } else {
            UserEntity userEntity = new UserEntity();
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());

            userEntity.setNickname(createUserRequest.getNickname());
            userEntity.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            userEntity.setRoles(roles);

            UserEntity createdUser = userRepository.save(userEntity);
            return createdUser;
        }
    }

    @Override
    public UserEntity createAdmin(UserEntity user) {
        Boolean existsByNickname = userRepository.existsByNickname(user.getNickname());
        if (existsByNickname) {
            throw new ValidationException("Nickname exists!");
        } else {
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());

            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            UserEntity createdAdmin = userRepository.save(user);
            return createdAdmin;
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
            courseService.deleteAllByUserId(userId);
            wordService.deleteAllByUserId(userId);
            userRepository.deleteById(userId);
        } else {
            throw new UsernameNotFoundException(format("User with userId - %d, not found", userId));
        }
    }

}
