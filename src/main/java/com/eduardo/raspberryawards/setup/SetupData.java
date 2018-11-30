package com.eduardo.raspberryawards.setup;

import com.eduardo.raspberryawards.dto.FlatFileMovieDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.model.Producer;
import com.eduardo.raspberryawards.model.Studio;
import com.eduardo.raspberryawards.service.MovieService;
import com.eduardo.raspberryawards.service.ProducerService;
import com.eduardo.raspberryawards.service.StudioService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.util.*;

@Component
public class SetupData {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private StudioService studioService;

    @Autowired
    private MovieService movieService;

    private HashMap<String, Producer> producers = new HashMap<>();
    private HashMap<String, Studio> studios = new HashMap<String, Studio>();

    private List<FlatFileMovieDTO> loadFlatFileMovieDTO(String fileName) throws Exception {
        List<FlatFileMovieDTO> flatFileMovieDTOS = new ArrayList<>();
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .typedSchemaFor(FlatFileMovieDTO.class)
                .withoutHeader()
                .withColumnSeparator(';')
                .withSkipFirstDataRow(true)
                .withoutQuoteChar();
        InputStream file = new ClassPathResource(fileName).getInputStream();
        MappingIterator<FlatFileMovieDTO  > dataIterator = csvMapper.readerFor(FlatFileMovieDTO.class).with(schema)
                .readValues(file);

        while(dataIterator.hasNext()){
            flatFileMovieDTOS.add(dataIterator.nextValue());
        }

        return flatFileMovieDTOS;
    }

    @PostConstruct
    public void importData() throws Exception{
        List<FlatFileMovieDTO> flatFileMovieDTOS = loadFlatFileMovieDTO("/static/movielist.csv");
        Collection<Producer> movieProducers;
        Collection<Studio> movieStudios;

        for(FlatFileMovieDTO flatFileMovieDTO:flatFileMovieDTOS){
            movieProducers = saveProducer(flatFileMovieDTO);
            movieStudios = saveStudio(flatFileMovieDTO);
            saveMovie(movieProducers, movieStudios,flatFileMovieDTO);
        }
    }

    private void saveMovie(Collection<Producer> movieProducers, Collection<Studio> movieStudios,
                           FlatFileMovieDTO flatFileMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(flatFileMovieDTO.getTitle());
        movie.setYear(Integer.valueOf(flatFileMovieDTO.getYear()));
        movie.setWinner(flatFileMovieDTO.getWinner().equalsIgnoreCase("yes") ? Boolean.TRUE:Boolean.FALSE);
        movie.setProducers(new HashSet<>(movieProducers));
        movie.setStudios(new HashSet<>(movieStudios));
        this.movieService.save(movie);
    }

    private Collection<Producer> saveProducer(FlatFileMovieDTO flatFileMovieDTO) {
        Collection<Producer> movieProducers = new ArrayList<>();
        String[] producerDescriptions = getProducerNames(flatFileMovieDTO);
        for(String producerName:producerDescriptions){
            producerName = producerName.trim();
            if(!producers.containsKey(producerName)) {
                Producer producer = createProducer(producerName);
                this.producers.put(producerName, producerService.save(producer));
            }
            movieProducers.add(producers.get(producerName));
        }
        return movieProducers;
    }

    private Producer createProducer(String producerName) {
        Producer producer = new Producer();
        producer.setName(producerName);
        return producer;
    }

    private String[] getStudioNames(FlatFileMovieDTO flatFileMovieDTO) {
        return flatFileMovieDTO.getStudios().split("[,]");
    }

    private Collection<Studio> saveStudio(FlatFileMovieDTO flatFileMovieDTO) {
        Collection<Studio> movieStudios = new ArrayList<>();
        String[] studioNames = getStudioNames(flatFileMovieDTO);
        for(String studioName:studioNames){
            studioName = studioName.trim();
            if(!studios.containsKey(studioName)) {
                Studio studio = createStudio(studioName);
                this.studios.put(studioName, studioService.save(studio));
            }
            movieStudios.add(this.studios.get(studioName));
        }
        return movieStudios;
    }

    private Studio createStudio(String studioName) {
        Studio studio = new Studio();
        studio.setName(studioName);
        return studio;
    }

    private String[] getProducerNames(FlatFileMovieDTO flatFileMovieDTO) {
        return flatFileMovieDTO.getProducers().split("[,]");
    }
}