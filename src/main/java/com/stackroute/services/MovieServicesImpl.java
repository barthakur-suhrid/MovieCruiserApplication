package com.stackroute.services;

import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class MovieServicesImpl implements MovieService{

//Put hardcode strings in properties
    private MovieRepository movieRepository;

    @Autowired
    public MovieServicesImpl(MovieRepository userRepository) {

        this.movieRepository =userRepository;

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
        if(movieRepository.existsById(movieId)&&comment!=null) {
            Movie movie1 = movieRepository.findById(movieId).get();
            movie1.setComment(comment);
            movieRepository.save(movie1);
            return true;
        }else{
            throw new MovieNotFoundException("Movie not found in database");
        }
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
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException{
        if(!movieRepository.existsById(movie.getId())){
            Movie savedMovie = movieRepository.save(movie);
            return savedMovie;
        }else{
            throw new MovieAlreadyExistsException("Movie already exists");
        }

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
