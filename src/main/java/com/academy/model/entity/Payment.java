package com.academy.model.entity;

import lombok.Data;

@Data
public class Payment {

    private Integer ID;
    private Integer orderID;
    private Integer paymentStatusID;

}
