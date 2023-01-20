package com.btc.moviechart.dto;

import java.util.Objects;

public class MovieDtoRequest {
    private String title;
    private int year;
    private double rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        if (!(o instanceof MovieDtoRequest that)) return false;
        return getYear() == that.getYear()
                && getRating() == that.getRating()
                && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getYear(), getRating());
    }

    @Override
    public String toString() {
        return "MovieDtoRequest{" +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
}
