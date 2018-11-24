package com.eduardo.raspberryawards.controller;

import com.eduardo.raspberryawards.model.Movie;
import com.eduardo.raspberryawards.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Component
@RequestMapping("/api/movie")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{publishYear}")
    public ResponseEntity<List<Movie>> findByPublishYear(@PathVariable(value = "publishYear") Integer publishYear){
        return ResponseEntity.ok(movieService.findByPublishYear(publishYear).get());
    }
}
