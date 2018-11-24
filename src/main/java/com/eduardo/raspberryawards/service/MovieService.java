package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<List<Movie>> findByPublishYear(Integer publishYear);
}
