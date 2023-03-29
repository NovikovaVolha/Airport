package com.academy.model.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Staff {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Integer genderId;
    private String title;

}
