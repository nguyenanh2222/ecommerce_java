package com.example.restservice.los_person.controller;

import com.example.restservice.RestServiceApplication;
import com.example.restservice.los_person.dto.LosPersonDto;

import com.example.restservice.los_person.service.LosPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class LosPersonController {

    @Autowired
    LosPersonService losPersonService;


    @PostMapping("/los/person")
    public ResponseEntity<LosPersonDto> create(
            @Valid
            @RequestBody LosPersonDto losPerson) {
        return losPersonService.create(losPerson);
    }

    @GetMapping("/los/persons")
    @ResponseBody
    public ResponseEntity<List<LosPersonDto>> losPersons(
            @RequestParam(name="page", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(name="pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name="sortField", defaultValue = "fullName", required = false) String sortField,
            @RequestParam(name="sortDir", defaultValue = "asc", required = false) String sortDir
    )
        {
        return losPersonService.getLosPersons(pageNum, pageSize, sortField, sortDir);
    }

    @GetMapping(value = "los/person")
    public ResponseEntity getPerson(
            @RequestParam(name="id", defaultValue = "1", required = false) Integer id){
        return losPersonService.getLosPersonById(id);
    }


//    @RequestMapping(value = "/los/person/put", method = RequestMethod.PUT)
//    public ResponseEntity<LosPersonEntity> updateLosPerson(
//            @PathVariable(value = "id") Long Id,
//            @Valid @RequestBody LosPersonEntity losPerson) {
//        losPerson = losPersonService.getOne(Id);
//        if(losPerson == null) {
//            return ResponseEntity.notFound().build();
//        }
//        LosPersonEntity updated = losPersonService.save(losPerson);
//        return ResponseEntity.ok(updated);

//
//    @RequestMapping(value = "/los/person/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<LosPersonEntity> deleteLosPerson(@PathVariable(value = "id") Long id) {
//        LosPersonEntity losPerson = losPersonService.getOne(id);
//        if(losPerson == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        losPersonService.delete(losPerson);
//        return ResponseEntity.ok().build();
}

