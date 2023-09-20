package com.example.demo3.repositories;

import com.example.demo3.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findByFullname(String fullname);

}
