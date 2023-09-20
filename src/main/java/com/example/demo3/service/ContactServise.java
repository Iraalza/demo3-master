package com.example.demo3.service;

import com.example.demo3.models.Contact;

public interface ContactServise {

    Contact getContact(int contact_id_first);

    Contact createContact(Contact contact);
}
