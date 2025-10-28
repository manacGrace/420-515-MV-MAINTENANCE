package com.example.backend.controller;

import com.example.backend.entities.Persons;
import com.example.backend.services.PersonsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
@CrossOrigin
public class PersonsController {

    private final PersonsService personsService;

    public PersonsController(PersonsService personsService) {
        this.personsService = personsService;
    }

    @GetMapping("/getPersons/{lastname}")
    public List<Persons> getPersons(@PathVariable String lastname) {
        return personsService.searchAllPersons(lastname);
    }
}
