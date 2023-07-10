package com.example.MovieArhciveProject.controller;
import com.example.MovieArhciveProject.models.Movie;
import com.example.MovieArhciveProject.models.UserEntity;
import com.example.MovieArhciveProject.security.SecurityUtil;
import com.example.MovieArhciveProject.service.MovieService;
import com.example.MovieArhciveProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MovieController {
    private MovieService movieService;
    private UserService userService;

    @Autowired
    public MovieController(MovieService movieService, UserService userService){

        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping("/movies")
    public String listMovies(Model model){
        UserEntity user = null;
        List<Movie> movies = movieService.findAllMovies();
        String email = SecurityUtil.getSessionUser();
        if(email!=null){
            user = userService.findByEmail(email);
        }

        model.addAttribute("user",user);
        model.addAttribute("movies",movies);
        return "movies-list";
    }

    @GetMapping("/movies/new")
    public String createMovieForm(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie",movie);
        String email = SecurityUtil.getSessionUser();
        if(email == null){
            return "redirect:/register";
        }
        return "movie-create";
    }

    @PostMapping("/movies/new")
    public String createMovie(@ModelAttribute("movie") Movie movie){
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/movie/{movieId}/edit")
    public String editMovie(@PathVariable("movieId") long movieId, Model model){
        Movie movie = movieService.findMovieById(movieId);
        if (isEmailNotExist(movie)) return "redirect:/movies";
        model.addAttribute("movie",movie);
        return "movie-edit";
    }

    private boolean isEmailNotExist(Movie movie) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = null;
        if(email == null){
            return true;
        }
        else {
            user = userService.findByEmail(email);
            if(movie.getCreatedBy().getId() == user.getId() || user.getRoles().get(0).getName().equals("ADMIN")){
                return false;
            }
        }
        return true;
    }

    @PostMapping("/movie/{movieId}/edit")
    public String editMovie(@PathVariable("movieId") long movieId, @ModelAttribute Movie movie) {
        movie.setId(movieId);
        if (isEmailNotExist(movie)) return "redirect:/movies";
        movieService.updateMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/movie/{movieId}/delete")
    public String deleteMovie(@PathVariable("movieId") long movieId, @ModelAttribute Movie movie) {
        movieService.delete(movieId);
        return "redirect:/movies";
    }
}
