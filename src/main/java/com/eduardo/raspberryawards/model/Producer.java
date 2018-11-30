package com.eduardo.raspberryawards.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "producers",fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Movie> movies;

    public void addMovies(Movie movie){
        if(this.movies == null){
            this.movies = new HashSet<>();
        }
        this.movies.add(movie);
    }
}
