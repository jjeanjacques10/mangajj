package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.Role;
import com.mangajj.mangacontrol.entity.UserEntity;
import com.mangajj.mangacontrol.gateway.repositories.UserRepository;
import com.mangajj.mangacontrol.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        Optional<UserEntity> userExists = userRepository.findByEmail(user.getEmail());

        if (userExists.isPresent()) throw new IllegalArgumentException("User " + user.getEmail() + " already exists");
        user.setId(null);
        user.setRoles(List.of(Role.builder().id(2L).name("USER").build()));
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User " + email + " not exists"));
    }

}
