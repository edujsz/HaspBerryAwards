package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.dto.WinnerYearDTO;
import com.eduardo.raspberryawards.model.Movie;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MovieService {

    Collection<Movie> findByPublishYear(Integer publishYear);

    Iterable<Movie> findAll();

    Movie save(Movie movie);

    List<WinnerYearDTO> findTop2WinnerYears();

}
