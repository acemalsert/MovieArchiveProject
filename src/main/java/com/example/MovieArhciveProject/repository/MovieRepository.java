package com.example.MovieArhciveProject.repository;

import com.example.MovieArhciveProject.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

}
