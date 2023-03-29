package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer ID;
    private OrderStatus orderStatus;
    private Passenger passenger;
    private Plane plane;
    private Route route;

}
