package com.eduardo.raspberryawards.controller;

import com.eduardo.raspberryawards.dto.MovieDTO;
import com.eduardo.raspberryawards.dto.WinnerDTO;
import com.eduardo.raspberryawards.exception.MovieIsWinnerException;
import com.eduardo.raspberryawards.exception.MovieNotFoundException;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.service.MovieService;
import com.eduardo.raspberryawards.setup.SetupData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Component
@RequestMapping("/api/movie")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    SetupData data;

    @RequestMapping(value = "/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MovieDTO>> findByPublishYear(@PathVariable(value = "year") Integer year){
        return ResponseEntity.ok(movieService.findByPublishYear(year));
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Movie>> findAll(){
        return ResponseEntity.ok(this.movieService.findAll());
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie){
        return ResponseEntity.ok(this.movieService.save(movie));
    }

    @GetMapping("/topWinners")
    public ResponseEntity<WinnerDTO> findTop2WinnerYears(){
        return ResponseEntity.ok(new WinnerDTO(this.movieService.findTop2WinnerYears()));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") Long id) throws MovieNotFoundException, MovieIsWinnerException {
        this.movieService.delete(id);
    }
    @GetMapping("/test")
    public void test(){
        data.test();
    }
}
