package com.stackroute.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.stackroute.MovieCruiserAppApplication;
import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
     }

    @PostMapping("/movie")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie){
            ResponseEntity responseEntity;
            try {
                movieService.saveMovie(movie);
                responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
            }catch(MovieAlreadyExistsException exception){
                logger.error("This is a debug msg inside getAllMovies() in Controller");

                responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
            }catch(Exception exception){
                responseEntity=new ResponseEntity<String>("Unsucessful creation",HttpStatus.CONFLICT);
            }

            return  responseEntity;
            }
    @GetMapping("/movie")
    public ResponseEntity<?> getAllMovies(){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
            return responseEntity;
        }catch (MovieNotFoundException ex){
            logger.error("This is a debub msg inside getAllMovies() in Controller");
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            return responseEntity;
        }catch(Exception exception){
            responseEntity=new ResponseEntity<String>("Unsucessful in fetching movie database",HttpStatus.CONFLICT);
            return responseEntity;
        }
    }

    @GetMapping("/movie/{movieName}")
    public  ResponseEntity<?> getMovieFromName(@PathVariable("movieName") String movieName){
        ResponseEntity responseEntity;
        try{
            responseEntity=new ResponseEntity<List<Movie>>(movieService.getMovieByName(movieName),HttpStatus.OK);
            return responseEntity;
        }catch(MovieNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            return responseEntity;
        }catch(Exception exception){
            responseEntity=new ResponseEntity<String>("Unsucessful in fetching",HttpStatus.CONFLICT);
            return responseEntity;
        }
    }

    @PutMapping("/movie/{id}")
    public  ResponseEntity<?> updateComment(@PathVariable("id") String movieId,@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            movieService.updateComments(movieId,movie.getComment());
            responseEntity=new ResponseEntity<String>("Comments successfully updated",HttpStatus.OK);
            return responseEntity;
        }catch(MovieNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            return responseEntity;
        }catch(Exception exception){
            responseEntity=new ResponseEntity<String>("Unsucessful updation",HttpStatus.CONFLICT);
            return responseEntity;
        }
    }

    @DeleteMapping("/movie/{id}")
    public  ResponseEntity<?> deleteMovie(@PathVariable("id") String movieId){
        ResponseEntity responseEntity;
        try{
            movieService.deleteMovie(movieId);
            responseEntity=new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);
            return responseEntity;
        }catch(MovieNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            return responseEntity;
        }catch(Exception exception){
            responseEntity=new ResponseEntity<String>("Unsucessful deletion",HttpStatus.CONFLICT);
            return responseEntity;
        }
    }


}
