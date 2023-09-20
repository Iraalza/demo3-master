package com.example.demo3.exceptions;

public class ContactNotFoundException extends RuntimeException{

    private final int contact_id_first;

    public ContactNotFoundException(int contact_id_first){
        this.contact_id_first = contact_id_first;
    }

    @Override
    public String getMessage(){
        return "Контакт с id = " + contact_id_first + "не найден!";
    }
}
