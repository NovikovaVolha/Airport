package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    private Integer id;
    private City departureCity;
    private Timestamp departureDateTime;
    private City arrivalCity;
    private Timestamp arrivalDateTime;

}
