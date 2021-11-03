package com.marcuschiu.example.graphqldgs.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private Show show;
    private String description;
}