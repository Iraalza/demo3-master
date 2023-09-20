package com.example.demo3.exceptions;

public class ChangeNotFoundException extends RuntimeException{

    private final int changeid;

    public ChangeNotFoundException(int changeid){
        this.changeid = changeid;
    }

    @Override
    public String getMessage(){
        return "Заявка с id = " + changeid + "не найдена!";
    }
}
