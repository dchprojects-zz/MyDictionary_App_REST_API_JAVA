package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;

import java.util.List;

public interface AdminService {

    public List<UserResponse> userList();

    public LanguageResponse createLanguage(CreateLanguageRequest createLanguageRequest);

    public UserResponse registerAdmin(CreateUserRequest createUserRequest);

}
