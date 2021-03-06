package com.marcuschiu.example.graphqldgs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Show {
    private String title;
    private String directorName;
    private Director director;
    private Integer releaseYear;
    private List<Review> reviews;

    public Show(String title, Integer releaseYear, String directorName) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.directorName = directorName;
        this.reviews = null;
    }
}