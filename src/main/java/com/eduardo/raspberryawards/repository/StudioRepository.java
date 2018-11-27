package com.eduardo.raspberryawards.repository;

import com.eduardo.raspberryawards.dto.StudioDTO;
import com.eduardo.raspberryawards.model.Studio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudioRepository extends CrudRepository<Long, Studio> {

    @Query(value = "SELECT new com.eduardo.raspberryawards.dto.StudioDTO (S.name, COUNT(1))" +
            " FROM Studio S " +
            " INNER JOIN S.movies M" +
            " WHERE M.winner = 1" +
            " GROUP BY S.name" +
            " ORDER BY 2 DESC")
    List<StudioDTO> findCountWinsForEachStudioOrderByWins();
}
