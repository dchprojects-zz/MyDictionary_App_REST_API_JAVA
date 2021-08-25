package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final WordRepository wordRepository;

    @Override
    public void deleteAccount(Long userId) {
        Boolean existsByUserId = userRepository.existsByUserId(userId);
        if (existsByUserId) {
            courseRepository.deleteAllByUserId(userId);
            wordRepository.deleteAllByUserId(userId);
            userRepository.deleteById(userId);
        } else {
            throw new NoSuchElementException("User not found!");
        }
    }

}
