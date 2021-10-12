package com.marcuschiu.example.graphqldgs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Director {
    private String name;
    private List<Show> shows;
}
