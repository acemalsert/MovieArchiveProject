package com.example.MovieArhciveProject.service;

import com.example.MovieArhciveProject.models.UserEntity;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    void saveUser(UserEntity userEntity);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
    /*UserEntity getLatestUser();*/
}
