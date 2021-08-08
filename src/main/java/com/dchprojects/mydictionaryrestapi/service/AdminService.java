package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;

import java.util.List;

public interface AdminService {

    public List<UserEntity> userList();

    public LanguageEntity createLanguage(CreateLanguageRequest createLanguageRequest);

    public UserEntity registerAdmin(CreateUserRequest createUserRequest);

}
