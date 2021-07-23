package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dchprojects.mydictionaryrestapi.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    public Boolean isExist(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return user != null;
    }

    public Boolean isExist(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.isPresent();
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }

    public UserEntity findById(Integer id) {
        return userRepository.findById(id).get();
    }

    public void updateUsername(Integer id, String username) {
        UserEntity user = userRepository.findById(id).get();
        user.setUsername(username);
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

}
