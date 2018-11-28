package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.dto.MovieDTO;
import com.eduardo.raspberryawards.dto.WinnerYearDTO;
import com.eduardo.raspberryawards.exception.MovieIsWinnerException;
import com.eduardo.raspberryawards.exception.MovieNotFoundException;
import com.eduardo.raspberryawards.model.Movie;

import java.util.Collection;
import java.util.List;

public interface MovieService {

    Collection<MovieDTO> findByPublishYear(Integer year);

    Iterable<Movie> findAll();

    Movie save(Movie movie);

    List<WinnerYearDTO> findTop2WinnerYears();

    public void delete(Long id) throws MovieNotFoundException, MovieIsWinnerException;

}
