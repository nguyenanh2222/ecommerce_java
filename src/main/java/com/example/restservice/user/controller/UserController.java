package com.example.restservice.user.controller;
import com.example.restservice.user.dto.UserDto;
import com.example.restservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user")
    public ResponseEntity<UserDto> create(
            @Valid
            @RequestBody UserDto losPerson) {
        return userService.create(losPerson);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<UserDto>> losPersons(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "sortField", defaultValue = "firstName", required = false) String sortField,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return userService.getUsers(pageNum, pageSize, sortField, sortDir);
    }

    @GetMapping(value = "user")
    public ResponseEntity getPerson(
            @RequestParam(name="id", defaultValue = "1", required = false) Integer id){
        return userService.getUserById(id);
    }
}