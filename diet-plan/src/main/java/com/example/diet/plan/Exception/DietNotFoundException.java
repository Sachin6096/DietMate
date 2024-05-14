package com.example.diet.plan.Exception;

public class DietNotFoundException extends RuntimeException{
    public DietNotFoundException(String messages){
        super(messages);
    }
}
