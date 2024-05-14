package com.example.diet.plan.RequestDTOs;

import com.example.diet.plan.Enums.MealType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class MealsDTO {

    private MealType mealType;

    private LocalTime time;

    private Integer routineId;

}
