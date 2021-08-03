package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> listAll();

    public Boolean isExist(String nickname);

    public Boolean isExist(Long userId);

    public Optional<UserEntity> findByNickname(String nickname);

    public void save(UserEntity user);

    public Optional<UserEntity> findById(Long userId);

    public void updateNickname(Long userId, String nickname);

    public void delete(Long userId);

}
