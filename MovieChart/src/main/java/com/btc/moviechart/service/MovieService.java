package com.btc.moviechart.service;

import com.btc.moviechart.dto.FavoriteDtoResponse;
import com.btc.moviechart.dto.MovieDtoRequest;
import com.btc.moviechart.dto.MovieDtoResponse;
import com.btc.moviechart.dto.MovieUpdateDtoRequest;
import com.btc.moviechart.model.Movie;
import com.btc.moviechart.repository.MovieRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final RankingService rankingService;

    public MovieService(MovieRepository movieRepository, RankingService rankingService) {
        this.movieRepository = movieRepository;
        this.rankingService = rankingService;
    }

    public MovieDtoResponse save(MovieDtoRequest movieDtoRequest) {
        Movie movie = this.movieRepository.save(buildMovie(movieDtoRequest));
        FavoriteDtoResponse favoriteDtoResponse = this.rankingService.save(movie.getId(), movieDtoRequest.getRating());
        return buildMovieDtoResponse(movie, favoriteDtoResponse.getRankOfMovie());
    }

    public MovieDtoResponse findByTitle(String title) {
        Movie movie = this.movieRepository.findByTitle(title)
                                          .orElseThrow(() -> new RuntimeException("Not found movie by title:" + title));
        FavoriteDtoResponse favoriteDtoResponse = this.rankingService.findById(movie.getId());
        return buildMovieDtoResponse(movie, favoriteDtoResponse.getRankOfMovie());
    }

    public List<MovieDtoResponse> getAll() {
        List<Movie> movies = this.movieRepository.findAll();
        Map<Integer, Integer> collectByMovieId = movies.stream()
                                                       .map(movie -> this.rankingService.findById(movie.getId()))
                                                       .collect(Collectors.toMap(
                                                               FavoriteDtoResponse::getMovieId,
                                                               FavoriteDtoResponse::getRankOfMovie));

        return movies.stream()
                     .map(movie -> {
                         int rankOfMovie = collectByMovieId.getOrDefault(movie.getId(), 0);
                         return buildMovieDtoResponse(movie, rankOfMovie);
                     })
                     .toList();
    }
    @Transactional
    public void deleteByTitle(String title) {
        Optional<Movie> movie = this.movieRepository.findByTitle(title);
        this.rankingService.deleteById(movie.orElseThrow(() -> new RuntimeException("Not found movie by title:" + title))
                                            .getId());
        this.movieRepository.deleteByTitle(title);
    }


    private Movie buildMovie(MovieDtoRequest movieDtoRequest) {
        return new Movie(movieDtoRequest.getTitle(), movieDtoRequest.getYear());
    }

    private MovieDtoResponse buildMovieDtoResponse(Movie movie, int rankOfMovie) {
        return new MovieDtoResponse(
                movie.getId(),
                rankOfMovie,
                movie.getTitle(),
                movie.getYear());
    }

    public MovieDtoResponse update(MovieUpdateDtoRequest movieUpdateDtoRequest) {
        Movie movie = this.movieRepository.findById(movieUpdateDtoRequest.getId())
                                          .orElseThrow(() -> new RuntimeException("Not found movie by id:" + movieUpdateDtoRequest.getId()));
        FavoriteDtoResponse favoriteDtoResponse = this.rankingService.save(movie.getId(), movieUpdateDtoRequest.getRating());
        return buildMovieDtoResponse(movie, favoriteDtoResponse.getRankOfMovie());
    }
}
