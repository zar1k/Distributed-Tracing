package com.btc.ranking.dto;

import java.util.Objects;

public class FavoriteDtoRequest {
    private int movieId;
    private double rating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof FavoriteDtoRequest that)) return false;
        return getMovieId() == that.getMovieId() && getRating() == that.getRating();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getRating());
    }

    @Override
    public String toString() {
        return "FavoriteDtoRequest{" +
                "movieId=" + movieId +
                ", rating=" + rating +
                '}';
    }
}