package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dchprojects.mydictionaryrestapi.entity.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) {
        return userRepository.findById(id).get();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

}
