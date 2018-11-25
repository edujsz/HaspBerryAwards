package com.eduardo.raspberryawards.repository;

import com.eduardo.raspberryawards.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Collection<Movie> findByPublishYear(Integer publishYear);

}
