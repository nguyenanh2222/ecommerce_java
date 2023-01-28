package com.example.restservice.user.service;

import com.example.restservice.user.dto.UserDto;
import com.example.restservice.user.entity.RoleEntity;
import com.example.restservice.user.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


public interface UserService {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity user);

    RoleEntity saveRoleEntity(RoleEntity role);
    void addRoleToUser(String username, String roelName );
    UserEntity getUser(String username);
    public List<UserEntity> getUsers();
    public List<UserEntity> getUsersAuth();
    public UserDto convertToDto(UserEntity userEntity);

    UserEntity convertToEntity(UserDto UserDto) throws ParseException;

    public default ResponseEntity<UserDto> create(@Valid UserDto UserDto) {
        return null;
    };


    ResponseEntity<List<UserDto>> getUsers(
            int pageNum, int pageSize, String sortField, String sortDir);

    public default ResponseEntity getUserById(Integer id) {
        return null;
    };
}
