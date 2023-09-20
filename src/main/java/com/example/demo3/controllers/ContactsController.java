package com.example.demo3.controllers;

import com.example.demo3.models.Change;
import com.example.demo3.models.Contact;
import com.example.demo3.repositories.ContactRepository;
import com.example.demo3.service.ContactServise;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactsController {

    private final ContactRepository repository;
    
    private final ContactServise contactServise;

    public ContactsController(ContactRepository repository, ContactServise contactServise){
        this.repository = repository;
        this.contactServise = contactServise;
    }

    @GetMapping("/contacts")
    Iterable<Contact> all(){return repository.findAll();}
    
    @GetMapping("/contact/{id}")
    public Contact getContact(@PathVariable int id){
        return contactServise.getContact(id);
    }

    @PostMapping("/addContact")
    Contact newContact(@RequestBody Contact newContact){
        Change.foo();
        return contactServise.createContact(newContact);
    }



}
