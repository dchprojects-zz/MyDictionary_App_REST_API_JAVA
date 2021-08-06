package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.AuthRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.AuthResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;

public interface AuthService {

    public AuthResponse login(AuthRequest request);

    public UserEntity register(CreateUserRequest createUserRequest);

    public UserEntity registerAdmin(CreateUserRequest createUserRequest);

}
