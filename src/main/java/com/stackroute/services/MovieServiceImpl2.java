package com.stackroute.services;

import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("movieServiceImpl2")
public class MovieServiceImpl2 implements MovieService{

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl2(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean deleteMovie(String movieId) throws MovieNotFoundException {

        if(movieRepository.existsById(movieId)) {
            Movie movie= movieRepository.findById(movieId).get();
            movieRepository.delete(movie);
            return true;
        }
        else{
            throw new MovieNotFoundException("Movie not found in database");
        }
    }

    @Override
    public boolean updateComments(String movieId, String comment) throws MovieNotFoundException{
        boolean status=false;
        if(movieRepository.existsById(movieId)&&comment!=null) {
            Movie movie1 = movieRepository.findById(movieId).get();
            movie1.setComment(comment);
            movieRepository.save(movie1);
            status= true;
        }else{
            throw new MovieNotFoundException("Movie not found in database");
        }
        return status;
    }

    @Override
    public List<Movie> getMovieByName(String movieName) throws MovieNotFoundException{
        List<Movie> movieList;
        if((movieList= movieRepository.findByMovieName(movieName)).size()!=0) {
            return movieList;
        }
        else{
            throw new MovieNotFoundException("Movie not found in database");
        }

    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        Movie savedMovie=null;

        if(!movieRepository.existsById(movie.getId())){
            savedMovie = movieRepository.save(movie);
        }else{
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        return savedMovie;
    }

    @Override
    public List<Movie> getAllMovies() throws MovieNotFoundException{
        List<Movie> movieList;
        if((movieList= movieRepository.findAll()).size()!=0){
            return movieList;
        }else{
            throw new MovieNotFoundException("Empty database");
        }
    }
}
