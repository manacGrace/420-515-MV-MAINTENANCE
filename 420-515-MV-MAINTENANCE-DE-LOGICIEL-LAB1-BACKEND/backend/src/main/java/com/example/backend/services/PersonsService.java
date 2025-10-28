package com.example.backend.services;

import com.example.backend.entities.Persons;
import com.example.backend.repositories.PersonsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsService {

    private final PersonsRepository personsRepository;

    public PersonsService(PersonsRepository personsRepository){
        this.personsRepository = personsRepository;
    }

    public List<Persons> searchAllPersons(String lastname){
        return personsRepository.findByLastnameStartingWith(lastname);
    }
}
