package com.example.restservice.los_person.entity;

import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Data
@Entity
@Table(name = "LOS_PERSON")
@NoArgsConstructor
public class LosPersonEntity {
        @Id
        @Column(name = "ID", nullable = false)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
        @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
        private Long id;
        @Basic
        @Email
        @Column(name = "EMAIL")
        private String email;
        @Basic
        @Column(name = "FULLNAME")
        private String fullName;
}

