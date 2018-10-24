package com.stackroute.repository;

import com.stackroute.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RunWith(SpringRunner.class)
@DataMongoTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    List<Movie> movies;

    @Before
    public void setUp() throws Exception {
        movie =new Movie();
        movie.setId("tt5676");
        movie.setMovieName("Hunters");
        movie.setMovieUrl("http://google.com");
        movie.setComment("Shit");
        movie.setRatings(7);
        movie.setYearOfRelease(2015);
        movies=new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        movieRepository.deleteAll();
    }

    @Test
    public void findByMovieNameTestSuccess() {
        movies.add(movie);
        movieRepository.save(movie);

        List<Movie> fetchMovies = movieRepository.findByMovieName(movie.getMovieName());
        Assert.assertEquals(movies.size(),fetchMovies.size());
        for(int i=0;i<movies.size();i++)
        Assert.assertEquals(movies.get(i).toString(),fetchMovies.get(i).toString());

    }

    @Test
    public void findByMovieNameTestFailure() {
        movies.add(movie);
        Movie testMovie = new Movie("tt5676","slaasda","ksjdk","random",7,2015);

        movies.add(testMovie);
        movieRepository.save(movie);
        movieRepository.save(testMovie);

        List<Movie> fetchMovies = movieRepository.findByMovieName(movie.getMovieName());
        Assert.assertEquals(0,fetchMovies.size());
    }

    @Test
    public void testSaveMovie(){
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getId()).get();
        Assert.assertEquals("tt5676",fetchMovie.getId());

    }

    @Test
    public void testSaveUserFailure(){
        Movie testMovie = new Movie("tt5676","slaasda","ksjdk","random",7,2015);
        movieRepository.save(testMovie);
        Movie fetchMovie = movieRepository.findById(testMovie.getId()).get();
        Assert.assertNotSame(movie.toString(),fetchMovie.toString());

    }

    @Test
    public void testDeleteMovieSuccess(){
        Movie movie1=movieRepository.save(movie);
       movieRepository.delete(movie1);
       Assert.assertFalse("Deleted unsuccessful",movieRepository.existsById(movie1.getId()));
    }

    @Test
    public void testDeleteMovieFailure(){
        Movie movie1=movieRepository.save(movie);
        Movie movie2=new Movie("tt5677","slaasda","ksjdk","random",7,2015);
        movieRepository.delete(movie2);
        Assert.assertTrue("Deleted successful",movieRepository.existsById(movie1.getId()));
    }

    @Test
    public void testGetAllUser(){
        Movie testMovie1 = new Movie("tt5568","http","ksjdk","random",7,2015);
        Movie testMovie2 = new Movie("tt5569","http","ksjdk","random",7,2015);
        movieRepository.save(testMovie1);
        movieRepository.save(testMovie2);
        movies.add(testMovie1);
        movies.add(testMovie2);

        List<Movie> list = movieRepository.findAll();

        Assert.assertEquals(2, list.size());

        for (int i=0;i<2;i++) {
            Assert.assertEquals(movies.get(i).toString(), list.get(i).toString());
        }

    }

}
