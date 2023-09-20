package com.example.demo3.service;

import com.example.demo3.exceptions.ContactNotFoundException;
import com.example.demo3.models.Contact;
import com.example.demo3.repositories.ContactRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ContactServiseImpl implements ContactServise{

    private final ContactRepository contactRepository;

    public  ContactServiseImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact getContact (int contact_id_first){
        return contactRepository.findById(contact_id_first).orElseThrow(() -> new ContactNotFoundException(contact_id_first));
    }

    @Override
    public Contact createContact (Contact contact){
        return  contactRepository.save(contact);
    }
}
