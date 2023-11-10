package com.example.Netflix_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    //ATRIBUTOS
    @Id
    private String id;
    private String name;
    private String coverUrl;
    private String director;
    private String genre;

    private String yearM;


/*
    public Movie() {
    }

    //CONSTRUCTOR
    public Movie(String name, String director, String genre, String id, String year,String coverUrl) {
        this.name = name;
        this.coverUrl = coverUrl;
        this.director = director;
        this.genre = genre;
        this.id = id;
        this.year=year;

    }


    public String getName() {
        return name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
   public String getYear() {
       return year;
   }

    public void setYear(String year) {
        this.year = year;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setId(String id) {
        this.id = id;
    }


 */
}
