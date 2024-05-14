package com.example.diet.plan.Model;

import com.example.diet.plan.Model.DietPlan;
import com.example.diet.plan.Model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DietPlanGenerater {

    public DietPlan generatePlan(User user){

        DietPlan dietPlan = DietPlan.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(2))
                .user(user).build();

        return dietPlan;
    }
}