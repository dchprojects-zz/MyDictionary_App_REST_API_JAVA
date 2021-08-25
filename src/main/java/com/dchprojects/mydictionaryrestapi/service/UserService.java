package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;

import java.util.List;

public interface UserService {

    public List<UserEntity> listAll();

    UserEntity findByNickname(String nickname);

    public UserEntity createUser(CreateUserRequest createUserRequest);

    public UserEntity createAdmin(CreateUserRequest createUserRequest);

    public UserEntity findById(Long userId);

}
