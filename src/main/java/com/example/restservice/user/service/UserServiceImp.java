package com.example.restservice.user.service;

import com.example.restservice.dao.Role;
import com.example.restservice.dao.User;
import com.example.restservice.dao.UserFriend;
import com.example.restservice.user.dto.UserDto;
import com.example.restservice.user.repository.UserRepository;
import com.example.restservice.user.repository.repo_auth.RoleRepo;
import com.example.restservice.user.repository.repo_auth.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    final private ModelMapper modelMapper;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto convertToDto(User userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public User convertToEntity(UserDto UserDto) throws ParseException {
        return modelMapper.map(UserDto, User.class);

    }
    @Override
    public ResponseEntity<UserDto> create(
            @Valid UserDto UserDto) {
        try {
            User userEntity = convertToEntity(UserDto);
            User userEntitySave = userRepository.save(userEntity);
            UserDto UserDtoSave = convertToDto(userEntitySave);
            return new ResponseEntity<>(UserDtoSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<List<UserDto>> getUsers(
            int pageNum, int pageSize, String sortField, String sortDir) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());

        Page<User> pageUsers = userRepository.findAll(pageable);

        List<User> users = pageUsers.getContent();

        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        if(userDtos.isEmpty()) {
            return new ResponseEntity<>(userDtos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    @Override
    public ResponseEntity getUserById(Integer id) {
        Optional<User> User = userRepository.findById(Long.valueOf(id));
        if(User.isPresent()){
            UserDto UserDto = convertToDto(User.get());
            return new ResponseEntity<>(UserDto, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("add new user to db {}", user.getUserName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {

        log.info("add new role to db {}", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public Role saveRoleEntity(Role role) {
        return null;
    }


    @Override
    public void addRoleToUser(String username, String name) {
        User user = userRepo.findByUserName(username);
        Role role = roleRepo.findByName(name);
        log.info("add new role to user to db");
        user.getRoles().add(role);
//        validation
    }

    @Override
    public User getUser(String username) {
        log.info("get user on db by username");
        return userRepo.findByUserName(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getUsersAuth() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user == null){
            log.error("user not found in database");
            throw new UsernameNotFoundException("user not found in database");
        }else{
            log.info("user found in database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}
