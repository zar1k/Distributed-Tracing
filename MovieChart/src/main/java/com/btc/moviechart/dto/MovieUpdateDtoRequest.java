package com.btc.moviechart.dto;

import java.util.Objects;

public class MovieUpdateDtoRequest {
    private int id;
    private long rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieUpdateDtoRequest that)) return false;
        return getId() == that.getId() && getRating() == that.getRating();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRating());
    }

    @Override
    public String toString() {
        return "MovieUpdateDtoRequest{" +
                "id=" + id +
                ", rating=" + rating +
                '}';
    }
}
