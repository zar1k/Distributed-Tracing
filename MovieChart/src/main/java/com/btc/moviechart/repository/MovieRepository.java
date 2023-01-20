package com.btc.moviechart.repository;

import com.btc.moviechart.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);

    void deleteByTitle(String title);
}
