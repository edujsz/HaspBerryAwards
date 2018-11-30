package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.dto.ProducerWinIntervalDTO;
import com.eduardo.raspberryawards.model.Producer;

import java.util.List;

public interface ProducerService {

    List<Producer> findAll();

    ProducerWinIntervalDTO localizeIntervals(List<Producer> producers);

    Producer save(Producer producer);
}
