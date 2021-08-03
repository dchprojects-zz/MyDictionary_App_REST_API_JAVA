package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MDUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

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
