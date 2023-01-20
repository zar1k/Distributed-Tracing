package com.btc.ranking.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

public class MovieRank implements Serializable {
    @Id
    private int movieId;
    private int rank;

    public MovieRank() {
    }

    public MovieRank(int movieId, int rank) {
        this.movieId = movieId;
        this.rank = rank;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieRank movieRank)) return false;
        return getMovieId() == movieRank.getMovieId() && getRank() == movieRank.getRank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getRank());
    }

    @Override
    public String toString() {
        return "MovieRank{" +
                "movieId=" + movieId +
                ", rank=" + rank +
                '}';
    }
}
