package com.example.restservice.user.service;

import com.example.restservice.dao.Role;
import com.example.restservice.dao.User;
import com.example.restservice.user.dto.UserDto;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


public interface UserService {
    User saveUser(User user);
    Role saveRole(Role user);

    Role saveRoleEntity(Role role);
    void addRoleToUser(String username, String roelName );
    User getUser(String username);
    public List<User> getUsers();
    public List<User> getUsersAuth();
    public UserDto convertToDto(User userEntity);

    User convertToEntity(UserDto UserDto) throws ParseException;

    public default ResponseEntity<UserDto> create(@Valid UserDto UserDto) {
        return null;
    };


    ResponseEntity<List<UserDto>> getUsers(
            int pageNum, int pageSize, String sortField, String sortDir);

    public default ResponseEntity getUserById(Integer id) {
        return null;
    };
}
