package com.eduardo.raspberryawards.service.impl;

import com.eduardo.raspberryawards.dto.ProducerDTO;
import com.eduardo.raspberryawards.dto.ProducerWinIntervalDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.model.Producer;
import com.eduardo.raspberryawards.repository.ProducerRepository;
import com.eduardo.raspberryawards.service.ProducerService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    public ProducerWinIntervalDTO localizeIntervals(){

        List<Producer> producers = this.producerRepository.findAll();
        ProducerWinIntervalDTO producerWinIntervalDTO = new ProducerWinIntervalDTO();
        List<ProducerDTO> producerDTOS = getIntervalForEachProducer(producers);
        findFinalIntervals(producerWinIntervalDTO, producerDTOS);

        return producerWinIntervalDTO;
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    private void findFinalIntervals(ProducerWinIntervalDTO producerWinIntervalDTO, List<ProducerDTO> producerDTOS) {
        Collections.sort(producerDTOS, Comparator.comparing(ProducerDTO::getInterval));
        producerWinIntervalDTO.setMin(producerDTOS.get(0));
        producerWinIntervalDTO.setMax(producerDTOS.get(calculateInterval(1, producerDTOS.size())));
    }

    private List<ProducerDTO> getIntervalForEachProducer(List<Producer> producers) {
        List<ProducerDTO> producerDTOS = new ArrayList<>();
        List<Movie> movies;
        for(Producer producer:producers){
            Hibernate.initialize(producers);
            movies = new ArrayList<>();
            movies.addAll(producer.getMovies());
            if(movies.size() > 1) {
                producerDTOS.addAll(this.localizIntervalsForEachProducer(movies, producer.getName()));
            }
        }
        return producerDTOS;
    }

    private List<ProducerDTO> localizIntervalsForEachProducer(List<Movie> moviesOrderedByYear, String producerName){
        ProducerDTO min = new ProducerDTO();
        ProducerDTO max = new ProducerDTO();

        Collections.sort(moviesOrderedByYear,Comparator.comparing(Movie::getYear));

        Integer previousWin = 0;
        Integer interval = 0;

        for(Movie movie:moviesOrderedByYear) {
            previousWin = initializePreviousWin(previousWin, movie);
            if (interval <= (calculateInterval(previousWin, movie.getYear()))) {
                interval = calculateInterval(previousWin, movie.getYear());
                setProducerDTOValues(producerName, min, previousWin, interval, movie);
                previousWin = movie.getYear();
            } if (interval >= (calculateInterval(previousWin, movie.getYear()))) {
                interval = calculateInterval(previousWin, movie.getYear());
                setProducerDTOValues(producerName, max, previousWin, interval, movie);
                previousWin = movie.getYear();
            }
        }

        List<ProducerDTO> producerDTOS = new ArrayList<>();
        producerDTOS.add(min);
        producerDTOS.add(max);

        return producerDTOS;
    }

    private int calculateInterval(Integer previousWin, Integer year) {
        return year - previousWin;
    }

    private Integer initializePreviousWin(Integer previousWin, Movie movie) {
        previousWin = previousWin == 0 ? movie.getYear() : previousWin;
        return previousWin;
    }

    private void setProducerDTOValues(String producerName, ProducerDTO min, Integer previousWin, Integer interval, Movie movie) {
        min.setInterval(interval);
        min.setPreviousWin(previousWin);
        min.setFollowingWin(movie.getYear());
        min.setProducer(producerName);
    }
}
