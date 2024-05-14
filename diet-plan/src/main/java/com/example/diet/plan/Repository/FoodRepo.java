package com.example.diet.plan.Repository;

import com.example.diet.plan.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<Food,Integer> {
}
