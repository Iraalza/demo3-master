package com.example.demo3.exceptions;

public class ConflictNotFoundException extends RuntimeException{

    private final int conflictId;

    public ConflictNotFoundException (int conflictId){
        this.conflictId = conflictId;
    }

    @Override
    public String getMessage () {
        return "Конфликт с id = " + conflictId + "не найден!";
    }
}
