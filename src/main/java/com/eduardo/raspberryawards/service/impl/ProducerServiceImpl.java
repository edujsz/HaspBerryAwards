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
        List<ProducerDTO> producerDTOS = getIntervalForEachProducer(producers);
        return findFinalIntervals(producerDTOS);
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    private ProducerWinIntervalDTO findFinalIntervals(List<ProducerDTO> producerDTOS) {
        ProducerWinIntervalDTO producerWinIntervalDTO = new ProducerWinIntervalDTO();
        Collections.sort(producerDTOS, Comparator.comparing(ProducerDTO::getInterval));
        producerWinIntervalDTO.setMin(producerDTOS.get(0));
        producerWinIntervalDTO.setMax(producerDTOS.get(producerDTOS.size()-1));

        return producerWinIntervalDTO;
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

        Movie previousMovieWin = moviesOrderedByYear.get(0);
        Integer interval = 0;
        Integer minInterval = 0;
        Integer maxInterval = 0;

        for(Movie movie:moviesOrderedByYear) {
            if (previousMovieWin.getId().equals(movie.getId())){
                setProducerDTOValues(producerName, min, previousMovieWin.getYear(), interval, movie);
                setProducerDTOValues(producerName, max, previousMovieWin.getYear(), interval, movie);
            }else{
                interval = calculateInterval(previousMovieWin.getYear(), movie.getYear());
                if (interval >= maxInterval) {
                    maxInterval = interval;
                    setProducerDTOValues(producerName, max, previousMovieWin.getYear(), interval, movie);
                }
                if (interval <= minInterval) {
                    minInterval = interval;
                    setProducerDTOValues(producerName, min, previousMovieWin.getYear(), interval, movie);
                }
                previousMovieWin = movie;
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

    private void setProducerDTOValues(String producerName, ProducerDTO min, Integer previousWin, Integer interval, Movie movie) {
        min.setInterval(interval);
        min.setPreviousWin(previousWin);
        min.setFollowingWin(movie.getYear());
        min.setProducer(producerName);
    }
}
