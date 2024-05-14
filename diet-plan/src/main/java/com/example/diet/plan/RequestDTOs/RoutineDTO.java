package com.example.diet.plan.RequestDTOs;

import com.example.diet.plan.Enums.WeekDay;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;

@Getter
@Setter
public class RoutineDTO {

    private WeekDay weekDay;

    private Integer dietplanId;
}
