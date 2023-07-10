package com.example.MovieArhciveProject.repository;

import com.example.MovieArhciveProject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

    boolean existsByName(String admin);
}
