package com.example.diet.plan.Exception;

public class FoodItemNotFound extends RuntimeException{

    public FoodItemNotFound(String message){
        super(message);
    }
}
