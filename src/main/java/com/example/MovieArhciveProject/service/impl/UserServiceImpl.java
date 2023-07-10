package com.example.MovieArhciveProject.service.impl;

import com.example.MovieArhciveProject.models.Role;
import com.example.MovieArhciveProject.models.UserEntity;
import com.example.MovieArhciveProject.repository.RoleRepository;
import com.example.MovieArhciveProject.repository.UserRepository;
import com.example.MovieArhciveProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveUser(UserEntity userEntity) {
        Role role = roleRepository.findByName("USER");
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList(role));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /*@Override
    public UserEntity getLatestUser() {
        List<UserEntity> users = userRepository.findAll();
        UserEntity latestUser = users.get(users.size()-1);
        return latestUser;
    }*/
}
