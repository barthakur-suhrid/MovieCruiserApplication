package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;

@Document(collection = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    String id;
    @NotNull
    String movieUrl;
    @NotNull
    String movieName;
    @NotNull
    String comment;
    @NotNull
    double ratings;
    @NotNull
    int yearOfRelease;

    /*public Movie() {
    }

    public Movie(String id, String movieUrl, String movieName, String comment, double ratings, int yearOfRelease) {
        this.id = id;
        this.movieUrl = movieUrl;
        this.movieName = movieName;
        this.comment = comment;
        this.ratings = ratings;
        this.yearOfRelease = yearOfRelease;
    }



    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", comment='" + comment + '\'' +
                ", ratings=" + ratings +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }*/
}
