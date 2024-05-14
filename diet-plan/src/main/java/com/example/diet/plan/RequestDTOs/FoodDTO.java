package com.example.diet.plan.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {

    private String name;

    private Double calories;

    private Double protein;

    private Integer mealId;
}
