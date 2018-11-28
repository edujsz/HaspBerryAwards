package com.eduardo.raspberryawards.service;

import com.eduardo.raspberryawards.dto.ProducerWinIntervalDTO;
import com.eduardo.raspberryawards.model.Producer;

public interface ProducerService {

    ProducerWinIntervalDTO localizeIntervals();

    Producer save(Producer producer);
}
