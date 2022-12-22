package com.example.restservice.user.dto;
import com.example.restservice.user.entity.UserEntity;
import lombok.Data;
import java.io.Serializable;



/**
 * A DTO for the {@link UserEntity} entity
 */
@Data

public class UserDto implements Serializable {
    private Long id;
    private String uuid;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String password;
    private String mobile;
    private String registeredAt;
    private String intro;
    private String profile;
    private String isActive;
    private String isReported;
    private String isBlocked;
    private String lastLogin;
}