package com.eduardo.raspberryawards.service.impl;

import com.eduardo.raspberryawards.dto.ProducerWinIntervalDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.model.Producer;
import com.eduardo.raspberryawards.model.Studio;
import com.eduardo.raspberryawards.service.ProducerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProducerServiceImplTest {

    @TestConfiguration
    static class ProducerServiceImplTestContextConfiguration{
        public ProducerService producerService() {
            return new ProducerServiceImpl();
        }
    }

    @Autowired
    TestEntityManager entityManager;

    @MockBean
    ProducerService producerService;

    public void setUp(){
        Movie movie = new Movie();
        Set<Producer> producers = new HashSet<>();
        Set<Studio> studios = new HashSet<>();

        studios.add(this.getStudio("Estudio1"));
        producers.add(this.getProducer("produtor1"));
        movie.setStudios(studios);
        movie.setProducers(producers);
        movie.setTitle("Filme1");
        movie.setWinner(true);
        movie.setYear(2000);
        saveMovie(movie);

        studios.add(this.getStudio("Estudio1"));
        producers.add(this.getProducer("produtor1"));
        movie.setStudios(studios);
        movie.setProducers(producers);
        movie.setTitle("Filme2");
        movie.setWinner(true);
        movie.setYear(2000);
        saveMovie(movie);

        studios.add(this.getStudio("Estudio1"));
        producers.add(this.getProducer("produtor2"));
        movie.setStudios(studios);
        movie.setProducers(producers);
        movie.setTitle("Filme3");
        movie.setWinner(true);
        movie.setYear(2000);
        saveMovie(movie);

        studios.add(this.getStudio("Estudio1"));
        producers.add(this.getProducer("produtor2"));
        movie.setStudios(studios);
        movie.setProducers(producers);
        movie.setTitle("Filme4");
        movie.setWinner(false);
        movie.setYear(2005);
        saveMovie(movie);

        studios.add(this.getStudio("Estudio1"));
        producers.add(this.getProducer("produtor2"));
        movie.setStudios(studios);
        movie.setProducers(producers);
        movie.setTitle("Filme5");
        movie.setWinner(true);
        movie.setYear(2010);
        saveMovie(movie);
    }

    @Test
    public void testMinInterval(){
        /*this.setUp();
        ProducerWinIntervalDTO intervalDTO = this.producerService.localizeIntervals();

        assertThat(intervalDTO.getMin().getInterval())
                .isEqualTo(0);*/
    }

   /* @Test
    public void testMaxInterval(){
        ProducerWinIntervalDTO intervalDTO = this.producerService.localizeIntervals();

        assertThat(intervalDTO.getMax().getInterval())
                .isEqualTo(10);
    }

    @Test
    public void testPreviousWin(){
        ProducerWinIntervalDTO intervalDTO = this.producerService.localizeIntervals();

        assertThat(intervalDTO.getMin())
        .isEqualTo(2000);
    }

    @Test
    public void testFollowingWin(){
        ProducerWinIntervalDTO intervalDTO = this.producerService.localizeIntervals();

        assertThat(intervalDTO.getMin())
                .isEqualTo(2000);
    }*/

    private void saveMovie(Movie movie) {
        this.entityManager.persist(movie);
        this.entityManager.flush();
    }

    private Producer getProducer(String producerName) {
        Producer producer = new Producer();
        producer.setName(producerName);
        producer = entityManager.persist(producer);
        entityManager.flush();
        return producer;
    }

    private Studio getStudio(String studioName) {
        Studio studio = new Studio();
        studio.setName(studioName);
        studio = entityManager.persist(studio);
        entityManager.flush();
        return studio;
    }
}
