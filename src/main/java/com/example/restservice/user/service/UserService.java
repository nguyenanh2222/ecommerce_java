package com.example.restservice.user.service;
import com.example.restservice.user.dto.UserDto;
import com.example.restservice.user.entity.UserEntity;
import com.example.restservice.user.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@Service
public class UserService extends UserDto {
    @Autowired
    UserRepository userRepository;

    @Autowired
    final private ModelMapper modelMapper;

    private UserDto convertToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    private UserEntity convertToEntity(UserDto UserDto) throws ParseException {
        return modelMapper.map(UserDto, UserEntity.class);

    }

    public ResponseEntity<UserDto> create(
            @Valid UserDto UserDto) {
        try {
            UserEntity userEntity = convertToEntity(UserDto);
            UserEntity userEntitySave = userRepository.save(userEntity);
            UserDto UserDtoSave = convertToDto(userEntitySave);
            return new ResponseEntity<>(UserDtoSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<UserDto>> getUsers(
            int pageNum, int pageSize, String sortField, String sortDir) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());

        Page<UserEntity> pageUsers = userRepository.findAll(pageable);

        List<UserEntity> users = pageUsers.getContent();

        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        if(userDtos.isEmpty()) {
            return new ResponseEntity<>(userDtos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    public ResponseEntity getUserById(Integer id) {
        Optional<UserEntity> User = userRepository.findById(Long.valueOf(id));
        if(User.isPresent()){
            UserDto UserDto = convertToDto(User.get());
            return new ResponseEntity<>(UserDto, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

