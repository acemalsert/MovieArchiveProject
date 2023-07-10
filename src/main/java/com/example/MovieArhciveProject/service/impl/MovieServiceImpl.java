package com.example.MovieArhciveProject.service.impl;

import com.example.MovieArhciveProject.models.Movie;
import com.example.MovieArhciveProject.models.UserEntity;
import com.example.MovieArhciveProject.repository.MovieRepository;
import com.example.MovieArhciveProject.repository.UserRepository;
import com.example.MovieArhciveProject.security.SecurityUtil;
import com.example.MovieArhciveProject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,UserRepository userRepository) {

        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Movie> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(email);
        movie.setCreatedBy(user);
        return movieRepository.save(movie);
    }

    @Override
    public Movie findMovieById(long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void delete(long movieId) {
        movieRepository.deleteById(movieId);
    }
}
