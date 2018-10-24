package com.stackroute.services;

import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;

import java.util.List;


public interface MovieService {

    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
    public List<Movie> getAllMovies() throws  MovieNotFoundException;
    public boolean deleteMovie(String movieId) throws MovieNotFoundException;
    public boolean updateComments(String movieId,String comment) throws MovieNotFoundException;
    public List<Movie> getMovieByName(String movieName)throws MovieNotFoundException;


}
