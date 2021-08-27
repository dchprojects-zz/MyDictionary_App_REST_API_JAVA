package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;
import com.dchprojects.mydictionaryrestapi.entity_converter.EntityConverter;
import com.dchprojects.mydictionaryrestapi.service.AdminService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final LanguageService languageService;

    @Override
    public List<UserResponse> userList() {
        return userService.listAll()
                .stream()
                .map(EntityConverter::userEntityToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageResponse createLanguage(CreateLanguageRequest createLanguageRequest) {
        try {
            return EntityConverter
                    .languageEntityToLanguageResponse(languageService.create(createLanguageRequest));
        } catch (ValidationException validationException) {
            throw new ValidationException(validationException.getLocalizedMessage());
        }
    }

    @Override
    public UserResponse registerAdmin(CreateUserRequest createUserRequest) {
        try {
            return EntityConverter
                    .userEntityToUserResponse(userService.createAdmin(createUserRequest));
        } catch (ValidationException validationException) {
            throw new ValidationException(validationException.getLocalizedMessage());
        }
    }

}
