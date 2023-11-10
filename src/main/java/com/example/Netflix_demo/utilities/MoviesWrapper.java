package com.example.Netflix_demo.utilities;

import com.example.Netflix_demo.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //genera getter, setter, equals, hashCode, and toString methods
@NoArgsConstructor // genera constructor
@AllArgsConstructor// genera constructor con los argumentos
public class MoviesWrapper {

    private String id;
    private int qty;
    private Iterable<Movie> movies  ;
}
