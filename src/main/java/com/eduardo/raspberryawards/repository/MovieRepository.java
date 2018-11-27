package com.eduardo.raspberryawards.repository;

import com.eduardo.raspberryawards.dto.WinnerYearDTO;
import com.eduardo.raspberryawards.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Collection<Movie> findByPublishYear(Integer publishYear);

    @Query(value = "SELECT new com.eduardo.raspberryawards.dto.WinnerYearDTO (M.publishYear, COUNT(M.publishYear))" +
            " FROM Movie M GROUP BY M.publishYear ORDER BY 2 DESC")
    List<WinnerYearDTO> findTop2WinnerYears(Pageable pageable);

}
