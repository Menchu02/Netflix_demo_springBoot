package com.example.Netflix_demo.restController;
import java.util.Optional;
import com.example.Netflix_demo.model.Movie;
import com.example.Netflix_demo.service.ActivityLogService;
import com.example.Netflix_demo.service.MovieService;
import com.example.Netflix_demo.utilities.MoviesWrapper;
import com.example.Netflix_demo.utilities.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Netflix_demo.model.Movie;
import com.example.Netflix_demo.model.ActivityLog;

import org.springframework.ui.Model;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;



@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin(origins = "*")
public class MovieRestController {

    @Autowired
    MovieService movieService;
    @Autowired
    ActivityLogService activityLogService;


    //CRUD

    //GETALL

    @GetMapping("/getAllMovies")
    public ResponseEntity<Iterable<Movie>> getAllMovies(HttpServletRequest request) {



        // query to service to get all movies
        Iterable<Movie> moviesFromService = movieService.getAllBooks();
        // call Utilities to create a log
        ActivityLog activityLog = Utilities.createLog(request,"getAllMovies",
                "books", "processing", "/api/v1/book/getAllMovies", "GET");
        HttpHeaders headers = Utilities.createHeader(activityLog);
        //Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        //System.out.println("Lapsed time: " + ( timestamp2.getNanos() - timestamp.getNanos()));

        if (moviesFromService != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(moviesFromService);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(moviesFromService);
        }
    }

    //GET BY ID

    @GetMapping("/getMovieById/{id}")
    public ResponseEntity<Movie>getBookById(HttpServletRequest request, @PathVariable String id){
        //llamo al servicio para encontrar por id
        Optional<Movie> movie = movieService.findMovieById(id);



        ActivityLog activityLog= Utilities.createLog(request, "getMovieId","movie","processing","api/v1/movie/getMovieById","Get");



        HttpHeaders headers= Utilities.createHeader(activityLog);

        if (movie != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(movie.get());
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(null);
        }

    }

    //DELETE BY ID

    @DeleteMapping("/deleteMovieById/{id}")
    public ResponseEntity<Movie> deleteBookById (HttpServletRequest request, @PathVariable String id){

        Optional<Movie> movie = movieService.deleteById(id);
        ActivityLog activityLog = Utilities.createLog(request,"deleteMovieById",
                "movie", "processing", "api/v1/book/deleteMovieById", "DELETE");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        if (movie != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(movie.get());
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(movie.get());
        }

    }

    //DELETE ALL


    @DeleteMapping("/deleteAllBooks")
    public ResponseEntity deleteAllMovies    ( HttpServletRequest request){


        //consulta para eliminar todos los libros

        long qty = movieService.qtyMovies();
        boolean deleted = movieService.deleteAllMovies();

        ActivityLog activityLog = Utilities.createLog(request,"deleteAllMovies",
                "books", "processing", "api/v1/book/deleteAllMovies", "DELETE");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        if (deleted) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            headers.add("qtyObjectsDeleted", String.valueOf(qty));
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(null);
        }
    }


    //CREATE


    @PostMapping(path = "/createMovie", consumes ="application/JSON")
    public ResponseEntity<Movie> addBook(HttpServletRequest request, @RequestBody Movie movie){

        ActivityLog activityLog = Utilities.createLog(request,"createBook",
                "books", "fail", "api/v1/book/createBook", "POST");


        HttpHeaders headers = Utilities.createHeader(activityLog);

        Movie movieCreated = movieService.createMovie(movie);


        if (movieCreated != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(movie);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(movie);
        }

    }

    //CREATE WRAPPER

    @PostMapping(path = "/createMovies", consumes = "application/JSON")
    public ResponseEntity<Iterable<Movie>> addMovies(HttpServletRequest request, @RequestBody MoviesWrapper moviesWrapper){
        ActivityLog activityLog = Utilities.createLog(request,"createMovies",
                "movies", "fail", "api/v1/movie/createMovies", "POST");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        Iterable<Movie>movies = moviesWrapper.getMovies();
        ArrayList createMovies = new ArrayList<>();
        for ( Movie movie: movies){
            Movie movieCreated= movieService.createMovie(movie);
            createMovies.add(movieCreated);
        }
            int createMoviesBty= createMovies.size();

        if(createMoviesBty>1){
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            headers.add("createdMoviesQty", String.valueOf(createMoviesBty));
            headers.add("toCreateBooksQty", String.valueOf(moviesWrapper.getQty()) );
            return ResponseEntity.accepted().headers(headers).body(createMovies);

        }else{

            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(null);


        }

    }

    //UPDATE


    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<Movie> upDateMovie ( HttpServletRequest request, @PathVariable String id,@RequestBody Movie movie){


        Movie movieToUpdate = movieService.upDateMovie(id, movie);

        ActivityLog activityLog = Utilities.createLog(request,"updateBook",
                "movies", "processing", "api/v1/book/updateMovie", "PUT");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        if (movieToUpdate != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(movie);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(movie);
        }
    }





}












