package com.btc.moviechart.dto;

import java.util.Objects;

public class MovieDtoResponse {
    private final int id;
    private final int rank;
    private final String title;
    private final int year;

    public MovieDtoResponse(int id, int rank, String title, int year) {
        this.id = id;
        this.rank = rank;
        this.title = title;
        this.year = year;
    }


    public int getId() {
        return id;
    }


    public int getRank() {
        return rank;
    }


    public String getTitle() {
        return title;
    }


    public int getYear() {
        return year;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDtoResponse that)) return false;
        return getId() == that.getId()
                && getRank() == that.getRank()
                && getYear() == that.getYear()
                && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRank(), getTitle(), getYear());
    }

    @Override
    public String toString() {
        return "MovieDtoResponse{" +
                "id=" + id +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
