package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;

import java.util.List;

public interface UserService {

    public List<UserResponse> listAll();

    UserResponse findByNickname(String nickname);

    public UserResponse createUser(CreateUserRequest createUserRequest);

    public UserResponse createAdmin(CreateUserRequest createUserRequest);

    public UserResponse findById(Long userId);

}
