package com.example.Netflix_demo.service;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Locale;

import com.example.Netflix_demo.model.Movie;

import com.example.Netflix_demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class MovieService {




        @Autowired
        MovieRepository movieRepository;
    //GET ALL
    public Iterable<Movie> getAllBooks() {

        return movieRepository.findAll();
    }

    //FIND BY ID

    public Optional<Movie> findMovieById(String id) {



        Optional<Movie> movieFound = movieRepository.findById(id);

        return movieFound;
    }

    //method that returns movie content
    public long qtyMovies() {



        return movieRepository.count();

    }


    //DELETE BY ID


    //DELETE ALL

    public Optional<Movie> deleteById(String id) {
        Optional<Movie> movieFound = findMovieById(id);

        if (movieFound.isPresent()) {
            movieRepository.deleteById(id);
            return movieFound;
        } else {
            return Optional.empty(); // Devuelve un Optional vacío en lugar de null
        }
    }


    public boolean deleteAllMovies() {


        movieRepository.deleteAll();
        long qty = qtyMovies();

        boolean deletedMovies = true;

        if (qty > 0) {
            deletedMovies = false;
        }
        return deletedMovies;


    }







    //CREATE

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }


    //public Movie createMovie(Movie movie) {
      //  boolean movieSaved = movieRepository.save(movie);
      //return movieSaved;


    //}
    //UPDATE
    public Movie upDateMovie(String id, Movie movie){
     Optional<Movie>movieFound = findMovieById(id);
     if(movieFound.isPresent()){
         Movie movieSaved = movieRepository.save(movie);
         return movieSaved;

        } else return null;

    }





}




















