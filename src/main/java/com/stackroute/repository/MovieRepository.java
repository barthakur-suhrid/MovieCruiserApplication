package com.stackroute.repository;

import com.stackroute.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {

   // @Query(value = "SELECT * FROM MOVIE WHERE MOVIE_NAME=?1",nativeQuery = true)
     List<Movie> findByMovieName(String movieName);


}
