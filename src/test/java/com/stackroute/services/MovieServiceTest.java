package com.stackroute.services;

import com.stackroute.domain.Movie;
import com.stackroute.exceptions.MovieAlreadyExistsException;
import com.stackroute.exceptions.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

        Movie movie;

        @Mock
        MovieRepository movieRepository;

        @InjectMocks
        MovieService movieService=new MovieServiceImpl2(movieRepository);

        List<Movie> movies;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movieService=new MovieServiceImpl2(movieRepository);
        movie=new Movie("tt56","movieLink","Inglory","Average",7.5,2014);
        movies=new ArrayList<>();
        movies.add(movie);

    }


    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {
        when(movieRepository.existsById(any())).thenReturn(false);
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);


    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.existsById(any())).thenReturn(true);
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);

        Assert.assertEquals(movie,savedMovie);
        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);


    }


    @Test
    public void getAllMoviesTestSuccess() throws MovieNotFoundException {
        when(movieRepository.findAll()).thenReturn(movies);
        List<Movie> moviesList=movieService.getAllMovies();
        Assert.assertEquals(movies.size(),moviesList.size());
        for (int i=0;i<movies.size();i++) {
            Assert.assertEquals(movies.get(i).toString(), moviesList.get(i).toString());
        }
        verify(movieRepository,times(1)).findAll();


    }

    @Test(expected = MovieNotFoundException.class)
    public void getAllMoviesTestFailure() throws MovieNotFoundException{

        when(movieRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        List<Movie> moviesList=movieService.getAllMovies();

        verify(movieRepository,times(1)).findAll();


    }

    @Test
    public void deleteMovieTestSuccess() throws MovieNotFoundException{
        when(movieRepository.existsById(any())).thenReturn(true);
        when(movieRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(movie));
        doNothing().when(movieRepository).delete((Movie) any());
        boolean success=movieService.deleteMovie(movie.getId());
        Assert.assertTrue("Movie deleted",success);

        verify(movieRepository,times(1)).existsById(any());

        verify(movieRepository,times(1)).findById(any());

        verify(movieRepository,times(1)).delete((Movie) any());


    }

    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieTestFailure() throws MovieNotFoundException{
        when(movieRepository.existsById(any())).thenReturn(false);
        when(movieRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(movie));
        doNothing().when(movieRepository).delete(any());
        boolean failure=movieService.deleteMovie(movie.getId());
        Assert.assertFalse(failure);

        verify(movieRepository,times(1)).existsById(any());

        verify(movieRepository,times(1)).findById(any());

        verify(movieRepository,times(1)).delete((Movie) any());



    }


    @Test
    public void updateCommentsTestSuccess() throws MovieNotFoundException{
        when(movieRepository.existsById( any())).thenReturn(true);
        when(movieRepository.findById( any())).thenReturn(java.util.Optional.ofNullable(movie));
        when(movieRepository.save((Movie)any())).thenReturn(movie);
        boolean success=movieService.updateComments(movie.getId(),"Ok");
        Assert.assertTrue("Comment updated",success);

        verify(movieRepository,times(1)).existsById(any());

        verify(movieRepository,times(1)).findById(any());

        verify(movieRepository,times(1)).save((Movie) any());

    }

    @Test(expected = MovieNotFoundException.class)
    public void updateCommentsTestFailure() throws MovieNotFoundException {

        when(movieRepository.existsById( any())).thenReturn(false);
        when(movieRepository.findById( any())).thenReturn(java.util.Optional.ofNullable(movie));
        when(movieRepository.save((Movie)any())).thenReturn(movie);
        boolean failure=movieService.updateComments(movie.getId(),"Ok");
        Assert.assertTrue("Comment updation failure",failure);

        verify(movieRepository,times(1)).existsById(any());
        verify(movieRepository,times(1)).findById(any());
        verify(movieRepository,times(1)).save((Movie) any());





    }

    @Test
    public void getMovieByNameTestSuccess() throws MovieNotFoundException{
        when(movieRepository.findByMovieName(any())).thenReturn(movies);
        List<Movie> listMovies=movieService.getMovieByName(movie.getMovieName());
        Assert.assertEquals(movies,listMovies);
        verify(movieRepository,times(1)).findByMovieName(any());


    }

    @Test(expected = MovieNotFoundException.class)
    public void getMovieByNameTestFaillure()throws MovieNotFoundException{
        when(movieRepository.findByMovieName(any())).thenReturn(Collections.EMPTY_LIST);
        List<Movie> listMovies=movieService.getMovieByName(movie.getMovieName());
        Assert.assertEquals(movies,listMovies);

        verify(movieRepository,times(1)).findByMovieName(any());

    }
}
