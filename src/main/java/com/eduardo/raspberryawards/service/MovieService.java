package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.model.Movie;

import java.util.Collection;
import java.util.Map;

public interface MovieService {

    Collection<Movie> findByPublishYear(Integer publishYear);

    Iterable<Movie> findAll();

    Movie save(Movie movie);

    public Map<Integer, Long> findTop2WinnerYears();
}
