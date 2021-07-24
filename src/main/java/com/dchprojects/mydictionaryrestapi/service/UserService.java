package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> listAll();

    public Boolean isExist(String username);

    public Boolean isExist(Integer id);

    public Optional<UserEntity> findByUsername(String username);

    public void save(UserEntity user);

    public Optional<UserEntity> findById(Integer id);

    public void updateUsername(Integer id, String username);

    public void delete(Integer id);

}
