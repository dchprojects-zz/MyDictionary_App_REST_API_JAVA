package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.WordServiceForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WordServiceForUserImpl implements WordServiceForUser {

    private final WordRepository wordRepository;

    @Override
    public void deleteAllByUserId(Long userId) {
        wordRepository.deleteAllByUserId(userId);
    }

}
