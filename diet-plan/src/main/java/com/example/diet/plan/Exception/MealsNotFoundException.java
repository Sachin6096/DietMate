package com.example.diet.plan.Exception;

public class MealsNotFoundException extends RuntimeException{

    public MealsNotFoundException(String message){
        super(message);
    }
}
