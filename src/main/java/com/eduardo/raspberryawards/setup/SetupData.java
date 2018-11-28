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

import java.io.File;
import java.util.*;

@Component
public class SetupData {

    @Autowired
    ProducerService producerService;

    @Autowired
    StudioService studioService;

    @Autowired
    MovieService movieService;

    private HashMap<String, Producer> producers = new HashMap<String, Producer>();
    private HashMap<String, Studio> studios = new HashMap<String, Studio>();

    public List<FlatFileMovieDTO> loadFlatFileMovieDTO(String fileName) {
        List<FlatFileMovieDTO> flatFileMovieDTOS = new ArrayList<>();
        try {

            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = csvMapper
                    .typedSchemaFor(FlatFileMovieDTO.class)
                    .withoutHeader()
                    .withColumnSeparator(';')
                    .withSkipFirstDataRow(true)
                    .withoutQuoteChar();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<FlatFileMovieDTO  > dataIterator = csvMapper.readerFor(FlatFileMovieDTO.class).with(schema)
                    .readValues(file);

            while(dataIterator.hasNext()){
                flatFileMovieDTOS.add(dataIterator.nextValue());
            }

            return flatFileMovieDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void test(){
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
        movie.setWinner(flatFileMovieDTO.getWinner().toLowerCase() == "yes" ? Boolean.TRUE:Boolean.FALSE);
        movie.setProducers(new HashSet<>(movieProducers));
        movie.setStudios(new HashSet<>(movieStudios));
        this.movieService.save(movie);
    }

    private Collection<Producer> saveProducer(FlatFileMovieDTO flatFileMovieDTO) {
        Collection<Producer> movieProducers = new ArrayList<>();
        String[] producerDescriptions = getProducerNames(flatFileMovieDTO);
        for(String producerName:producerDescriptions){
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
        return flatFileMovieDTO.getProducers().split("[,]");
    }

    private Collection<Studio> saveStudio(FlatFileMovieDTO flatFileMovieDTO) {
        Collection<Studio> movieStudios = new ArrayList<>();
        String[] studioNames = getProducerNames(flatFileMovieDTO);
        for(String studioName:studioNames){
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