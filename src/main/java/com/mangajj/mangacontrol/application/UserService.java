package com.mangajj.mangacontrol.application;

import com.mangajj.mangacontrol.adapter.persistence.entity.UserEntity;

public interface UserService {

    UserEntity save(UserEntity user);

    UserEntity getUserByEmail(String email);

}
