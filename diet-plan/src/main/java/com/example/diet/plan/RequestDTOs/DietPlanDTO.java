package com.example.diet.plan.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DietPlanDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer userId;
}
