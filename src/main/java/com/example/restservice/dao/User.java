package com.example.restservice.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
    private Long id;
    @Basic
    @Column(name = "uuid", unique = true)
    private String uuid;
    @Basic
    @Email
    @Column(name = "email", unique = true)
    private String email;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "middle_name")
    private String middleName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Basic
    @Column(name = "password", nullable = false, unique = true)
    private String password;
    @Basic
    @Column(name= "mobile", nullable = false)
    private String mobile;
    @Basic
    @Column(name= "registered_at", nullable = false)
    private String registeredAt;
    @Basic
    @Column(name= "intro", nullable = false)
    private String intro;
    @Basic
    @Column(name= "profile", nullable = false)
    private String profile;
    @Basic
    @Column(name= "is_active", nullable = false)
    private String isActive;
    @Basic
    @Column(name= "is_reported", nullable = false)
    private String isReported;
    @Basic
    @Column(name= "is_blocked", nullable = false)
    private String isBlocked;
    @Basic
    @Column(name= "last_login", nullable = false)
    private String lastLogin;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}


