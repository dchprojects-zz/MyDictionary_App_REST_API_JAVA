package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.AdminService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final LanguageService languageService;

    @Override
    public List<UserEntity> userList() {
        return userService.listAll();
    }

    @Override
    public LanguageEntity createLanguage(CreateLanguageRequest createLanguageRequest) {
        try {
            return languageService.create(createLanguageRequest);
        } catch (ValidationException validationException) {
            throw new ValidationException(validationException.getLocalizedMessage());
        }
    }

    @Override
    public UserEntity registerAdmin(CreateUserRequest createUserRequest) {
        try {
            return userService.createAdmin(createUserRequest);
        } catch (ValidationException validationException) {
            throw new ValidationException(validationException.getLocalizedMessage());
        }
    }

}
