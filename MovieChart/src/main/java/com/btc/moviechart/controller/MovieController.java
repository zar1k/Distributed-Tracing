package com.btc.moviechart.controller;

import com.btc.moviechart.dto.MovieDtoRequest;
import com.btc.moviechart.dto.MovieDtoResponse;
import com.btc.moviechart.dto.MovieUpdateDtoRequest;
import com.btc.moviechart.service.MovieService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDtoResponse> create(@RequestBody MovieDtoRequest movieDtoRequest) {
        MovieDtoResponse movieDtoResponse = this.movieService.save(movieDtoRequest);
        return ResponseEntity.ok(movieDtoResponse);
    }

    @PutMapping
    public ResponseEntity<MovieDtoResponse> update(@RequestBody MovieUpdateDtoRequest movieUpdateDtoRequest) {
        MovieDtoResponse movieDtoResponse = this.movieService.update(movieUpdateDtoRequest);
        return ResponseEntity.ok(movieDtoResponse);
    }

    @GetMapping("/{title}")
    public ResponseEntity<MovieDtoResponse> findByTitle(@PathVariable String title) {
        MovieDtoResponse movieDtoResponse = this.movieService.findByTitle(title);
        return ResponseEntity.ok(movieDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<MovieDtoResponse>> findAll() {
        List<MovieDtoResponse> responses = this.movieService.getAll();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteByTitle(@PathVariable String title) {
        this.movieService.deleteByTitle(title);
        return ResponseEntity.ok()
                             .build();
    }
}
