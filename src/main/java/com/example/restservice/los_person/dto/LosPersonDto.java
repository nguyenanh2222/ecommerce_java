package com.example.restservice.los_person.dto;

import com.example.restservice.los_person.entity.LosPersonEntity;
import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;


/**
 * A DTO for the {@link com.example.restservice.los_person.entity.LosPersonEntity} entity
 */
@Data

public class LosPersonDto implements Serializable {

    private Long id;
    private String email;
    private String fullName;
    private String userName;
    private String password;


}