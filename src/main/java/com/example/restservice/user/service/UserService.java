package com.example.restservice.user.service;

import com.example.restservice.user.dto.UserDto;
import com.example.restservice.user.entity.RoleEntity;
import com.example.restservice.user.entity.UserEntity;
import com.example.restservice.user.repository.repo_auth.UserRepo;
import org.jwt.auth.domain.Role;
import org.jwt.auth.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService {
    UserEntity saveUserEntity(UserEntity user);
    RoleEntity saveRoleEntity(RoleEntity role);
    void addRoleToUser(String username, String roelName );
    UserEntity getUser(String username);
    public List<UserDto> getUsers();
    public List<UserEntity> getUsersAuth();
    public UserDto convertToDto(UserEntity userEntity);

    UserEntity convertToEntity(UserDto UserDto) throws ParseException;

    public default ResponseEntity<UserDto> create(@Valid UserDto UserDto) {};


    ResponseEntity<List<UserDto>> getUsers(
            int pageNum, int pageSize, String sortField, String sortDir);

    public default ResponseEntity getUserById(Integer id) {
        return null;
    };
}
