package com.eduardo.raspberryawards.controller;

import com.eduardo.raspberryawards.dto.WinnerYearDTO;
import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@Component
@RequestMapping("/api/movie")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/{publishYear}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Movie>> findByPublishYear(@PathVariable(value = "publishYear") Integer publishYear){
        return ResponseEntity.ok(movieService.findByPublishYear(publishYear));
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
    public ResponseEntity<List<WinnerYearDTO>> findTop2WinnerYears(){
        return ResponseEntity.ok(this.movieService.findTop2WinnerYears());
    }

}
