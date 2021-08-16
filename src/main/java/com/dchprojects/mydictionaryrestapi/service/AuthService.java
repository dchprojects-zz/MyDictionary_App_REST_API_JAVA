package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.AuthRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.AuthResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;

public interface AuthService {

    public AuthResponse login(AuthRequest request);

    public AuthResponse register(CreateUserRequest createUserRequest);

}
