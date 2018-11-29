package com.eduardo.raspberryawards.repository;

import com.eduardo.raspberryawards.dto.MovieDTO;
import com.eduardo.raspberryawards.dto.WinnerYearDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.model.Producer;
import com.eduardo.raspberryawards.model.Studio;
import com.eduardo.raspberryawards.repository.MovieRepository;
import com.eduardo.raspberryawards.service.MovieService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MovieServiceRespositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void whenFindByYear_thenReturnMovies(){
        this.saveOneMovie();
        Collection<Movie> movies = this.movieRepository.findByYear(2018);
        assertThat(movies.size())
                .isEqualTo(1);

    }

    @Test public void whenFindTop2Winner_thenReturWinnerYearDTOs(){
        this.saveMovies();
        List<WinnerYearDTO> winnerYearDTOS = this.movieRepository.findTop2WinnerYears(PageRequest.of(0,2));
        assertThat(winnerYearDTOS.size())
                .isEqualTo(2);
    }

    private void saveMovies(){
        Movie movie = new Movie();
        Set<Producer> producers = getProducers();
        Set<Studio> studios = getStudios();
        movie.setWinner(true);
        movie.setYear(2017);
        movie.setTitle("Avengers Age of Ultron");
        movie = setAdditionalMovieProperties(producers, studios, movie);
        saveMovie(movie);

        movie = new Movie();
        movie.setWinner(true);
        movie.setYear(2018);
        movie.setTitle("Ant Man and the Wasp");
        movie = setAdditionalMovieProperties(producers, studios, movie);
        saveMovie(movie);
    }

    private void saveOneMovie(){
        Movie movie = new Movie();;
        Set<Producer> producers = getProducers();
        Set<Studio> studios = getStudios();
        movie.setWinner(true);
        movie.setYear(2018);
        movie.setTitle("Avengers Infinity War");
        movie = setAdditionalMovieProperties(producers, studios, movie);
        saveMovie(movie);
    }

    private void saveMovie(Movie movie) {
        entityManager.persist(movie);
        entityManager.flush();
    }

    private Movie setAdditionalMovieProperties(Set<Producer> producers, Set<Studio> studios, Movie movie) {
        movie.setProducers(producers);
        movie.setStudios(studios);
        return movie;
    }

    private Set<Producer> getProducers() {
        Set<Producer> producers = new HashSet<>();
        Producer producer = new Producer();
        producer.setName("Stan Lee");
        return producers;
    }

    private Set<Studio> getStudios() {
        Set<Studio> studios = new HashSet<>();
        Studio studio = new Studio();
        studio.setName("Marvel");
        return studios;
    }
}
