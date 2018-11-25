package com.eduardo.raspberryawards.service.impl;

import com.eduardo.raspberryawards.dto.WinnerYearDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.repository.MovieRepository;
import com.eduardo.raspberryawards.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Collection<Movie> findByPublishYear(Integer publishYear) {
        return movieRepository.findByPublishYear(publishYear);
    }

    @Override
    public Iterable<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie save(Movie movie){
       return this.movieRepository.save(movie);
    }

    @Override
    public Map<Integer, Long> findTop2WinnerYears(){
        Collection<Movie> movies = new ArrayList<>();
        this.findAll().iterator().forEachRemaining(movies::add);
        movies
                .stream()
                .filter(movie -> (movie.isWinner()))
                .map(Collectors.groupingBy(Movie::getPublishYear, Collectors.counting()));
        return null;
    }
}