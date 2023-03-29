package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plane {

    private Integer id;
    private String manufacturer;
    private String model;
    private String seats;

}
