package com.eduardo.raspberryawards.repository;

import com.eduardo.raspberryawards.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<List<Movie>> findByPublishYear(Integer publishYear);
}
