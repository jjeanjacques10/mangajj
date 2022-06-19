package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.UserEntity;

public interface UserService {

    UserEntity save(UserEntity user);

    UserEntity getUserByEmail(String email);

}
