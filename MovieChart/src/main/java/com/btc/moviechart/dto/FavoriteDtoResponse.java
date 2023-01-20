package com.btc.moviechart.dto;

import java.util.Objects;

public class FavoriteDtoResponse {
    private int movieId;
    private int rankOfMovie;

    public FavoriteDtoResponse() {
    }

    public FavoriteDtoResponse(int movieId, int rankOfMovie) {
        this.movieId = movieId;
        this.rankOfMovie = rankOfMovie;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRankOfMovie() {
        return rankOfMovie;
    }

    public void setRankOfMovie(int rankOfMovie) {
        this.rankOfMovie = rankOfMovie;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof FavoriteDtoResponse that)) return false;
        return getMovieId() == that.getMovieId() && getRankOfMovie() == that.getRankOfMovie();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getRankOfMovie());
    }

    @Override
    public String toString() {
        return "FavoriteDtoResponse{" +
                "movieId=" + movieId +
                ", rankOfMovie=" + rankOfMovie +
                '}';
    }
}
