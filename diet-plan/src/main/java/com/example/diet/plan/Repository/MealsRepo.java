package com.example.diet.plan.Repository;

import com.example.diet.plan.Model.Meals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealsRepo extends JpaRepository<Meals,Integer> {
}
