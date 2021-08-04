package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateNicknameRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> listAll();

    public Boolean existsByNickname(String nickname);

    public Boolean existsByUserId(Long userId);

    public Optional<UserEntity> findByNickname(String nickname);

    public UserEntity createUser(CreateUserRequest createUserRequest);

    public UserEntity createAdmin(CreateUserRequest createUserRequest);

    public Optional<UserEntity> findById(Long userId);

    public UserEntity updateNickname(Long userId, UpdateNicknameRequest updateNicknameRequest);

    public void delete(Long userId);

}
