package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    private Integer ID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Integer genderID;
    private String email;
    private String address;

}
