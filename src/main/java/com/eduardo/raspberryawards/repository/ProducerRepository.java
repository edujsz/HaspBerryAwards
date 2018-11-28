package com.eduardo.raspberryawards.repository;

import com.eduardo.raspberryawards.model.Producer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducerRepository extends CrudRepository<Producer,Long> {
    List<Producer> findAll();
}
