package com.example.backend.repositories;

import com.example.backend.entities.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {
    List<Persons> findByLastnameStartingWith(String lastname);
}
