package com.example.backend.services;

import com.example.backend.repositories.SeriesRepository;
import com.example.backend.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
