package com.btc.ranking.dto;

import java.util.Objects;

public class FavoriteDtoResponse {
    private final int movieId;
    private final int rankOfMovie;


    public FavoriteDtoResponse(int movieId, int rankOfMovie) {
        this.movieId = movieId;
        this.rankOfMovie = rankOfMovie;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getRankOfMovie() {
        return rankOfMovie;
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

