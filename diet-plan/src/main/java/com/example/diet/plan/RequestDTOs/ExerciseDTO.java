package com.example.diet.plan.RequestDTOs;

import com.example.diet.plan.Enums.ExerciseIntensity;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class ExerciseDTO {
    private String name;

    private Duration duration;

    private ExerciseIntensity exerciseIntensity;

    private Integer routineId;

}
