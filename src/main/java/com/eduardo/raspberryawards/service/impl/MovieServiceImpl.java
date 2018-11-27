package com.eduardo.raspberryawards.service.impl;

import com.eduardo.raspberryawards.dto.WinnerYearDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.repository.MovieRepository;
import com.eduardo.raspberryawards.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
    public List<WinnerYearDTO> findTop2WinnerYears(){
        Movie movie = new Movie();
        return movieRepository.findTop2WinnerYears(PageRequest.of(0,2));
    }
}