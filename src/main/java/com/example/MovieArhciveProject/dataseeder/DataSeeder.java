package com.example.MovieArhciveProject.dataseeder;

import com.example.MovieArhciveProject.models.Role;
import com.example.MovieArhciveProject.models.UserEntity;
import com.example.MovieArhciveProject.repository.RoleRepository;
import com.example.MovieArhciveProject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository roleRepository, UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!roleRepository.existsByName("ADMIN")) {
            Role adminRole = new Role();
            adminRole.setId(1);
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
            UserEntity adminUser = new UserEntity();
            adminUser.setId(1);
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("password"));
            adminUser.getRoles().add(adminRole);
            userRepository.save(adminUser);
        }

        if (!roleRepository.existsByName("USER")) {
            Role userRole = new Role();
            userRole.setId(2);
            userRole.setName("USER");
            roleRepository.save(userRole);
        }
    }
}
