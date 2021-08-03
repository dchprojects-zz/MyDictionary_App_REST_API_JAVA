package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Boolean userIsExists = userRepository.existsByNickname(s);
        if (userIsExists) {
            UserEntity userEntity = userRepository.findByNickname(s).get();
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            userEntity.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
            });
            return new User(userEntity.getNickname(), userEntity.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
