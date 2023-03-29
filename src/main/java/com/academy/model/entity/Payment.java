package com.academy.model.entity;

import lombok.Data;

@Data
public class Payment {

    private Integer id;
    private Integer orderId;
    private Integer paymentStatusId;

}
