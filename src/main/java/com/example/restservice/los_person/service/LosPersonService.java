package com.example.restservice.los_person.service;

import com.example.restservice.los_person.dto.LosPersonDto;
import com.example.restservice.los_person.entity.LosPersonEntity;
import com.example.restservice.los_person.repository.LosPersonRepository;
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
public class LosPersonService extends LosPersonDto {
    @Autowired
    LosPersonRepository losPersonRepository;

    @Autowired
    final private ModelMapper modelMapper;

    private LosPersonDto convertToDto(LosPersonEntity losPersonEntity) {
        return modelMapper.map(losPersonEntity, LosPersonDto.class);
    }

    private LosPersonEntity convertToEntity(LosPersonDto losPersonDto) throws ParseException {
        return modelMapper.map(losPersonDto, LosPersonEntity.class);

    }

    public ResponseEntity<LosPersonDto> create(
            @Valid LosPersonDto losPersonDto) {
        try {
            LosPersonEntity losPersonEntity = convertToEntity(losPersonDto);
            LosPersonEntity losPersonEntitySave = losPersonRepository.save(losPersonEntity);
            LosPersonDto losPersonDtoSave = convertToDto(losPersonEntitySave);
            return new ResponseEntity<>(losPersonDtoSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<LosPersonDto>> getLosPersons(
            int pageNum, int pageSize, String sortField, String sortDir) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());

        Page<LosPersonEntity> pagelosPersons = losPersonRepository.findAll(pageable);

        List<LosPersonEntity> losPersons = pagelosPersons.getContent();

        List<LosPersonDto> losPersonDtos = losPersons.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        if(losPersonDtos.isEmpty()) {
            return new ResponseEntity<>(losPersonDtos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(losPersonDtos, HttpStatus.OK);
    }

    public ResponseEntity getLosPersonById(Integer id) {
        Optional<LosPersonEntity> losPerson = losPersonRepository.findById(Long.valueOf(id));
        if(losPerson.isPresent()){
            LosPersonDto losPersonDto = convertToDto(losPerson.get());
            return new ResponseEntity<>(losPersonDto, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}

