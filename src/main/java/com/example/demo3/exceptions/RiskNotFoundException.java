package com.example.demo3.exceptions;

import com.example.demo3.models.Risk;

public class RiskNotFoundException extends RuntimeException{

    private final int riskId;

    public RiskNotFoundException(int riskId){
        this.riskId = riskId;
    }

    @Override
    public String getMessage(){
        return "Риск с id = " + riskId + "не найден!";
    }

    public static String getError(){return "Риск не в подходящем статусе";}
}
