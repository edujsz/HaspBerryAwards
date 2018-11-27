package com.eduardo.raspberryawards.service.impl;

import com.eduardo.raspberryawards.dto.ProducerDTO;
import com.eduardo.raspberryawards.dto.ProducerWinIntervalDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.model.Producer;
import com.eduardo.raspberryawards.model.Studio;
import com.eduardo.raspberryawards.repository.ProducerRepository;
import com.eduardo.raspberryawards.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    public ProducerWinIntervalDTO localizeIntervals(){

        List<Producer> producers = new ArrayList<>();
        this.producerRepository.findAll().iterator().forEachRemaining(producer -> {producers.add(producer);});
        ProducerWinIntervalDTO producerWinIntervalDTO = new ProducerWinIntervalDTO();
        List<ProducerDTO> producerDTOS = new ArrayList<>();
        List<Movie> movies;

        for(Producer producer:producers){
            movies = new ArrayList<>();
            movies.addAll(producer.getMovies());
            producerDTOS.addAll(this.localizIntervalsForEachProducer(movies));
        }

        Collections.sort(producerDTOS,Comparator.comparing(ProducerDTO::getInterval));

        producerWinIntervalDTO.setMax(producerDTOS.get(producerDTOS.size()));
        producerWinIntervalDTO.setMax(producerDTOS.get(0));

        return producerWinIntervalDTO;
    }

    private List<ProducerDTO> localizIntervalsForEachProducer(List<Movie> moviesOrderedByYear){
        ProducerDTO min = new ProducerDTO();
        ProducerDTO max = new ProducerDTO();

        Collections.sort(moviesOrderedByYear,Comparator.comparing(Movie::getYear));

        Integer previousWin = 0;
        Integer interval = 0;

        for(Movie movie:moviesOrderedByYear) {
            if (interval < (movie.getYear() - previousWin)) {
                interval = movie.getYear() - previousWin;
                min.setInterval(interval);
                min.setPreviousWin(previousWin);
                min.setFollowingWin(movie.getYear());
                //TODO
                min.setProducer("MIN");
            } else if (interval > (movie.getYear() - previousWin)) {
                interval = movie.getYear() - previousWin;
                min.setInterval(interval);
                min.setPreviousWin(previousWin);
                min.setFollowingWin(movie.getYear());
                //TODO
                min.setProducer("MAX");
                previousWin = movie.getYear();
            }
        }

        List<ProducerDTO> producerDTOS = new ArrayList<>();
        producerDTOS.add(min);
        producerDTOS.add(max);

        return producerDTOS;
    }
}
