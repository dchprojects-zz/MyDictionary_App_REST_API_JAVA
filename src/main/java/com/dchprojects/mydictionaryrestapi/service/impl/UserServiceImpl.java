package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean isExist(String nickname) {  return userRepository.existsByNickname(nickname); }

    @Override
    public Boolean isExist(Long userId) { return userRepository.existsByUserId(userId); }

    @Override
    public Optional<UserEntity> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void updateNickname(Long userId, String username) {
        UserEntity user = userRepository.findById(userId).get();
        user.setNickname(username);
        userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}
