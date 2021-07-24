package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.WordEntity;

import java.util.List;
import java.util.Optional;

public interface WordService {

    public List<WordEntity> listAll();

    public Boolean isExist(Integer id);

    public Optional<WordEntity> findById(Integer id);

    public Optional<WordEntity> findByUserId(Integer id);

    public void save(WordEntity word);

    public void update(WordEntity word);

    public void delete(Integer id);

}
