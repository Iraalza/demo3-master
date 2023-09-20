package com.example.demo3.controllers;

import com.example.demo3.models.Conflict;
import com.example.demo3.repositories.ChangeRepository;
import com.example.demo3.repositories.ConflictRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConflictController {

    private final ConflictRepository conflictRepository;

    public ConflictController(ConflictRepository conflictRepository){
        this.conflictRepository = conflictRepository;

    }

    @GetMapping("/conflicts")
    Iterable<Conflict> all(){return  conflictRepository.findAll();}
}

