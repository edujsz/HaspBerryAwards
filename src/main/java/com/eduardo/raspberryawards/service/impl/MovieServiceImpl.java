package com.eduardo.raspberryawards.service.impl;

import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.repository.MovieRepository;
import com.eduardo.raspberryawards.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Optional<List<Movie>> findByPublishYear(Integer publishYear) {
        return movieRepository.findByPublishYear(publishYear);
    }
}
