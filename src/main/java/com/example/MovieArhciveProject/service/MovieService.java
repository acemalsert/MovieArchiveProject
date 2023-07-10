package com.example.MovieArhciveProject.service;

import com.example.MovieArhciveProject.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAllMovies();
    Movie saveMovie(Movie movie);
    Movie findMovieById(long movieId);
    void updateMovie(Movie movie);
    void delete(long movieId);
}
