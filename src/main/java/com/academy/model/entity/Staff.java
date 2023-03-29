package com.academy.model.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Staff {

    private Integer ID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Integer genderID;
    private String title;

}
